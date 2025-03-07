package securityJwt;

import br.com.matheus.cadastro.vo.ClienteVO;
import br.com.matheus.cadastro.vo.security.TokenVO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import exceptions.InvalidJwtAuthenticationException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

public class JwtTokenProvider {

    @Value("${security.jwt.token.security-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:secret}")
    private long validadeEmMilisegundos = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO criandoTokenAcesso(String usuario, List<String> roles) {
        Date agora = new Date();
        Date validade = new Date(agora.getTime() + validadeEmMilisegundos);
        var tokenAcesso = pegarTokenAcesso(usuario, roles, agora, validade);
        var tokenAtualizado = pegarTokenAtualizado(usuario, roles, agora);

        return new TokenVO(usuario, true, agora, validade, tokenAcesso, tokenAtualizado);
    }

    public TokenVO atualizarToken(String atualizarToken) {

        if (atualizarToken.contains("Bearer ")) {
            atualizarToken = atualizarToken.substring("Bearer ".length());
        }
        JWTVerifier verificacao = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verificacao.verify(atualizarToken);
        String usuario = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        return criandoTokenAcesso(usuario, roles);
    }

    private String pegarTokenAcesso(String usuario, List<String> roles, Date agora, Date validade) {
        String issuerUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(agora)
                .withExpiresAt(validade)
                .withSubject(usuario)
                .withIssuer(issuerUrl)
                .sign(algorithm)
                .strip();
    }

    private String pegarTokenAtualizado(String usuario, List<String> roles, Date agora) {
        Date validarTokenAtualizado = new Date(agora.getTime() + validadeEmMilisegundos * 3);
        return JWT.create()
                .withClaim("roles", roles)
                .withIssuedAt(agora)
                .withExpiresAt(validarTokenAtualizado)
                .withSubject(usuario)
                .sign(algorithm)
                .strip();
    }

    public Authentication pegarAutorizacao (String token){
        DecodedJWT decodedJWT = decodificarToken(token);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodificarToken (String token){
        Algorithm alg = Algorithm.HMAC256(secretKey.getBytes());
        JWTVerifier verifier = JWT.require(alg).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT;
    }

    public String resolverToken (HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")){
            String subString = bearerToken.substring("Bearer ".length());
            return subString;
        }
        return null;
    }

    public boolean validadeToken (String token){
        DecodedJWT decodedJWT = decodificarToken(token);
        try {
            if (decodedJWT.getExpiresAt().before(new Date())){
                return  false;
            }
            return true;
        }catch (Exception ex){
            throw new InvalidJwtAuthenticationException("Token JWT expirado ou invalido");
        }
    }
}
