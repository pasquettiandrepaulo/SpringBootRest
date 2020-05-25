package com.andre.compasso.repository.cidade;

import com.andre.compasso.entity.cidade.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICidadeRepository extends CrudRepository<Cidade, Integer>, JpaRepository<Cidade, Integer> {

    @Query("SELECT c FROM Cidade c WHERE c.nome LIKE %?1%")
    List<Cidade> getCidadesByNome(String nome);

    @Query("SELECT c FROM Cidade c WHERE c.estado LIKE %?1%")
    List<Cidade> getCidadesByEstado(String estado);
}
