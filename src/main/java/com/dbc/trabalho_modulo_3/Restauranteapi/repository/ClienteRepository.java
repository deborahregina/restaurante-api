package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public interface  ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query(value = "select * " +
            "         from CLIENTE" +
            "        where upper(nome) like upper(:nome)",
            countQuery = "select count(*) " +
                    "         from CLIENTE" +
                    "        where upper(nome) like upper(:nome)",
            nativeQuery = true)
    Page<ClienteEntity> findByNomeNativo(String nome, Pageable pageable);

}

