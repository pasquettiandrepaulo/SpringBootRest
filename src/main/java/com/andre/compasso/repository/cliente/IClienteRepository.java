package com.andre.compasso.repository.cliente;

import com.andre.compasso.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClienteRepository extends CrudRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.nomeCompleto LIKE %?1%")
    List<Cliente> getClientesByNomeCompleto(String nome);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Cliente c SET c.nomeCompleto = ?2 WHERE c.id = ?1")
    void alterarNome(Integer id, String nome);
}
