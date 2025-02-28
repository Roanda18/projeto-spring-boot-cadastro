package br.com.matheus.cadastro.repositories;

import br.com.matheus.cadastro.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT p FROM Cliente p WHERE p.nome LIKE LOWER(CONCAT ('%',:nome,'%'))")
    Page<Cliente> findPersonByName(@Param("nome")String nome, Pageable pageable);
}
