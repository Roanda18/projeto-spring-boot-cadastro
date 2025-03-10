package br.com.matheus.cadastro.vo.security;

public class ContasCredenciadasVO {

    private String nomeUsuario;
    private String senha;

    public ContasCredenciadasVO() {
    }

    public ContasCredenciadasVO(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
