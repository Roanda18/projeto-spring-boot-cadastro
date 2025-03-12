package br.com.matheus.cadastro.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;
    @Column(nullable = false, length = 200)
    private String nome;
    @Column(name = "data_aniversario", nullable = false)
    private LocalDate dataAniversario;
    @Column(nullable = false, length = 200)
    private BigDecimal cpf;
    @Column(nullable = false, length = 300)
    private String endereco;

    public Cliente() {
    }

    public Cliente(BigDecimal id, String nome, LocalDate dataAniversario, BigDecimal cpf, String endereco) {
        this.id = id;
        this.nome = nome;
        this.dataAniversario = dataAniversario;
        this.cpf = cpf;
        this.endereco = endereco;
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
