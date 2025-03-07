package br.com.matheus.cadastro.services;

import br.com.matheus.cadastro.repositories.UsuariorRepository;
import br.com.matheus.cadastro.vo.security.ContasCredenciadasVO;
import br.com.matheus.cadastro.vo.security.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import securityJwt.JwtTokenProvider;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UsuariorRepository usuariorRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity logar(ContasCredenciadasVO dados){
        try{
            var nomeUsuario = dados.getNomeUsuario();
            var senha = dados.getSenha();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(nomeUsuario,senha));

            var usuario = usuariorRepository.findByUserName(nomeUsuario);
            var respostaToken = new TokenVO();

            if (usuario != null){
                respostaToken = jwtTokenProvider.criandoTokenAcesso(nomeUsuario, usuario.getRoles());
            }else {
                throw new UsernameNotFoundException("Usuario" + nomeUsuario + " não encotrado");
            }
            return ResponseEntity.ok(respostaToken);
        }catch (Exception ex){
            throw new BadCredentialsException("Usuario/senha invalidos");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity tokenAtualizado (String nomeUsuario, String tokenAtualizado){

        var user = usuariorRepository.findByUserName(nomeUsuario);
        var respostaToken = new TokenVO();

        if (user != null) {
            respostaToken = jwtTokenProvider.atualizarToken(tokenAtualizado);
        } else {
            throw new UsernameNotFoundException("Username " + nomeUsuario + " not found!");
        }

        return ResponseEntity.ok(respostaToken);
    }

}
