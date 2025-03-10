package br.com.matheus.cadastro.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_usuario", unique = true)
    private String nomeUsuario;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "senha")
    private String senha;

    @Column(name = "conta_nao_expirada")
    private Boolean contaNaoExpirada;

    @Column(name = "conta_nao_bloqueada")
    private Boolean contaNaoBloqueada;

    @Column(name = "credencial_nao_expirada")
    private Boolean credencialNaoExpirada;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permisao_usuario"
            , joinColumns = {@JoinColumn(name = "id_usuario")}
            , inverseJoinColumns = {@JoinColumn(name = "id_permissao")}
    )
    private List<Permissao> permissions;

    public Usuario() {
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        for (Permissao permissao : permissions){
            roles.add((permissao.getDescricao()));
        }
        return roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nomeUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.contaNaoExpirada;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.contaNaoBloqueada;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credencialNaoExpirada;
    }

    @Override
    public boolean isEnabled() {
        return this.ativo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getContaNaoExpirada() {
        return contaNaoExpirada;
    }

    public void setContaNaoExpirada(Boolean contaNaoExpirada) {
        this.contaNaoExpirada = contaNaoExpirada;
    }

    public Boolean getContaNaoBloqueada() {
        return contaNaoBloqueada;
    }

    public void setContaNaoBloqueada(Boolean contaNaoBloqueada) {
        this.contaNaoBloqueada = contaNaoBloqueada;
    }

    public Boolean getCredencialNaoExpirada() {
        return credencialNaoExpirada;
    }

    public void setCredencialNaoExpirada(Boolean credencialNaoExpirada) {
        this.credencialNaoExpirada = credencialNaoExpirada;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Permissao> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissao> permissions) {
        this.permissions = permissions;
    }
}
