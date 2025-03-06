package br.com.matheus.cadastro.vo.security;

import java.util.Date;

public class TokenVO {

    private String usuario;
    private Boolean autorizacao;
    private Date gerado;
    private Date expirado;
    private String TokenAcesso;
    private String TokenAtualizacao;

    public TokenVO(){}

    public TokenVO(String usuario, Boolean autorizacao, Date gerado, Date expirado, String tokenAcesso, String tokenAtualizacao) {
        this.usuario = usuario;
        this.autorizacao = autorizacao;
        this.gerado = gerado;
        this.expirado = expirado;
        TokenAcesso = tokenAcesso;
        TokenAtualizacao = tokenAtualizacao;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(Boolean autorizacao) {
        this.autorizacao = autorizacao;
    }

    public Date getGerado() {
        return gerado;
    }

    public void setGerado(Date gerado) {
        this.gerado = gerado;
    }

    public Date getExpirado() {
        return expirado;
    }

    public void setExpirado(Date expirado) {
        this.expirado = expirado;
    }

    public String getTokenAcesso() {
        return TokenAcesso;
    }

    public void setTokenAcesso(String tokenAcesso) {
        TokenAcesso = tokenAcesso;
    }

    public String getTokenAtualizacao() {
        return TokenAtualizacao;
    }

    public void setTokenAtualizacao(String tokenAtualizacao) {
        TokenAtualizacao = tokenAtualizacao;
    }
}
