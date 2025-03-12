package br.com.matheus.cadastro.controllers;

import br.com.matheus.cadastro.services.EnderecoServices;
import br.com.matheus.cadastro.vo.EnderecoVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/endereco/v1")
@Tag(name = "Endereço", description = "Endpoint para consultar endereço por CEP ")
public class EnderecoController {

    private final EnderecoServices cepService;

    public EnderecoController(EnderecoServices cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<EnderecoVO> consultarCep(@PathVariable String cep) {
        // Validação simples do formato do CEP
        if (!cep.matches("^\\d{5}-?\\d{3}$")) {
            return ResponseEntity.badRequest().build();
        }

        EnderecoVO endereco = cepService.consultarCep(cep.replace("-", ""));
        return ResponseEntity.ok(endereco);
    }
}
