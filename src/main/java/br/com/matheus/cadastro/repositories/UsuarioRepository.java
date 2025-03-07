package br.com.matheus.cadastro.repositories;

import br.com.matheus.cadastro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM User u WHERE u.nomeUsuario=:nomeUsuario")
    Usuario findByUserName(@Param("nomeUsuario")String nomeUsuario);
}