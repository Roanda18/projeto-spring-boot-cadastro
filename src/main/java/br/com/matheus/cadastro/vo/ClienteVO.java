package br.com.matheus.cadastro.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Schema
@JsonPropertyOrder({"id","nome","data_aniversario","cpf","endereco"})
public class ClienteVO extends RepresentationModel<ClienteVO> {

    @JsonProperty("id")
    private BigDecimal key;
    private String nome;
    private LocalDate dataAniversario;
    private BigDecimal cpf;
    private String endereco;

    public ClienteVO() {
    }

    public BigDecimal getKey() {
        return key;
    }

    public void setKey(BigDecimal key) {
        this.key = key;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataAniversario() {
        return dataAniversario;
    }

    public void setDataAniversario(LocalDate dataAniversario) {
        this.dataAniversario = dataAniversario;
    }

    public BigDecimal getCpf() {
        return cpf;
    }

    public void setCpf(BigDecimal cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
