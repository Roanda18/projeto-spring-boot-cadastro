package br.com.matheus.cadastro.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Date;

@Schema
@JsonPropertyOrder({"id","nome","data_aniversario","cpf","endereco"})
public class ClienteVO extends RepresentationModel<ClienteVO> {

    @JsonProperty("id")
    private BigDecimal key;
    private String nome;
    private Date data_aniversario;
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

    public Date getData_aniversario() {
        return data_aniversario;
    }

    public void setData_aniversario(Date data_aniversario) {
        this.data_aniversario = data_aniversario;
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
