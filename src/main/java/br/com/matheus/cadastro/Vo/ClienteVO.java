package br.com.matheus.cadastro.Vo;

import java.math.BigDecimal;
import java.util.Date;

public class ClienteVO {

    private BigDecimal id;
    private String nome;
    private Date data_aniversario;
    private BigDecimal cpf;
    private String endereco;

    public ClienteVO() {
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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
