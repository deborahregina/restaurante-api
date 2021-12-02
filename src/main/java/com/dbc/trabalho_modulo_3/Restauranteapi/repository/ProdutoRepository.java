package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface ProdutoRepository  extends JpaRepository<ProdutoEntity,Integer> {

    @Query("select p " +
            " from PRODUTO p " +
            "where tipoProduto = :tipoProduto ")
    Page<ProdutoEntity> findByTipoProduto(TipoProduto tipoProduto, Pageable pageable);

    @Query(value = "select * from produto p where p.valor_unitario < 50 ", nativeQuery = true)
    List<ProdutoEntity> buscaPromocao();
}
