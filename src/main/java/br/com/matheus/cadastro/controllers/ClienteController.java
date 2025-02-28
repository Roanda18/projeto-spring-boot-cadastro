package br.com.matheus.cadastro.controllers;


import br.com.matheus.cadastro.util.MediaType;
import br.com.matheus.cadastro.vo.ClienteVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/cliente/v1")
@Tag(name = "Cliente", description = "Endpoint para gerenciar clientes ")
public class ClienteController {

    @Autowired
    private ClienteServices servico;

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca por todos os cliente", description = "Busca por todos os cliente", tags = {"Cliente"})
    @ApiResponses(value = {@ApiResponse(description = "Success", responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteVO.class)))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Erro", responseCode = "500", content = @Content)})

    public ResponseEntity<PagedModel<EntityModel<ClienteVO>>> buscarTodos(

            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "15") Integer limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
        return ResponseEntity.ok(servico.buscartodos(pageable));
    }

    @GetMapping(value = "/buscaClientePorNome/{nome}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Procurar cliente por nome", description = "Procurar cliente por nome", tags = {"Cliente"})
    @ApiResponses(value = {@ApiResponse(description = "Success", responseCode = "200", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClienteVO.class)))}),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Erro", responseCode = "500", content = @Content)})

    public ResponseEntity<PagedModel<EntityModel<ClienteVO>>> buscaClientePorNome(

            @PathVariable(value = "nome") String nome,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "limit", defaultValue = "15") Integer limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction
    ) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
        return ResponseEntity.ok(servico.buscaClineteNome(nome, pageable));
    }

    @Operation(summary = "Busca cliente por ID", description = "Busca cliente por ID", tags = {"Cliente"})
    @ApiResponses(value = {
            @ApiResponse(description = "success", responseCode = "200", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON, schema =
                    @Schema(implementation = ClienteVO.class))}),

            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Erro", responseCode = "500", content = @Content)
    })

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/{id}")

    public ClienteVO buscaPorId(@PathVariable BigDecimal id) {
        return servico.buscaClienteId(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Operation(summary = "Adicionar um cliente novo", description = "Adicionar um cliente novo", tags = {"Cliente"}, responses = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Erro", responseCode = "500", content = @Content)
    })

    public ClienteVO criar(@RequestBody ClienteVO cliente) {
        return servico.criar(cliente);

    }

    @CrossOrigin(origins = {"http://localhost:8080"})
    @PutMapping(produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    @Operation(summary = "Alterar os dados de registro de um cliente  por ID", description = "Alterar os dados de registro de um cliente  por ID", tags = {"Cliente"}, responses = {
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Erro", responseCode = "500", content = @Content)
    })

    public ClienteVO alterar(@RequestBody ClienteVO cliente) {
        return servico.alterar(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletar cliente passando ID", description = "Deletar cliente passando ID", tags = {"Cliente"}, responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Erro", responseCode = "500", content = @Content)
    })

    public ResponseEntity<?> deletar(@PathVariable BigDecimal id){
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

