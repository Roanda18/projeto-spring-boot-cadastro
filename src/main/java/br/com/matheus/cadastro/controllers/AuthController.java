package br.com.matheus.cadastro.controllers;

import br.com.matheus.cadastro.services.AuthServices;
import br.com.matheus.cadastro.vo.security.ContasCredenciadasVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Autencicação EndPoint")
@RestController
@RequestMapping("/autenticacao")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Autentica o usuario e retorna o token")
    @PostMapping(value = "/logar")
    public ResponseEntity logar(@RequestBody ContasCredenciadasVO dados) {
        if (checarParametrosEstaoVazios(dados)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição do cliente invalida");
        }
        var token = authServices.logar(dados);
        if (token == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição do cliente invalida");
        } else {
            return token;
        }
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Atualizar token para autenticar usuario e retornar o token")
    @PutMapping(value = "/atualizar/{nomeusuario}")
    public ResponseEntity requisitarToken(@PathVariable("nomeusuario") String nomeUsuario, @RequestHeader("Autorizado") String tokenAtualizado) {
        if (checarParametrosEstaoVazios(nomeUsuario, tokenAtualizado)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição do cliente invalida");
        }
        var token = authServices.tokenAtualizado(nomeUsuario, tokenAtualizado);
        if (tokenAtualizado == null || tokenAtualizado.isBlank() || nomeUsuario == null || nomeUsuario.isBlank()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição do cliente invalida");
        }
        return token;
    }

    private boolean checarParametrosEstaoVazios(String nomeUsuario, String tokenAtualizado) {
        return tokenAtualizado == null || tokenAtualizado.isBlank() || nomeUsuario == null || nomeUsuario.isBlank();
    }

    private static boolean checarParametrosEstaoVazios(ContasCredenciadasVO dados) {
        return dados == null || dados.getNomeUsuario() == null || dados.getNomeUsuario().isBlank() || dados.getSenha() == null || dados.getSenha().isBlank();
    }
}
