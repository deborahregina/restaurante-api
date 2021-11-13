package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity,Integer> {

    @Query(value = "select * from pedido_produto pp where pp.id_produto = :idProduto AND pp.id_pedido = :idPedido",nativeQuery = true)
    List<PedidoProdutoEntity> buscaPorProdutoEPedido(Integer idProduto, Integer idPedido);

    @Query(value = "SELECT * from PEDIDO_PRODUTO pp where pp.id_produto = :idProduto", nativeQuery = true)
    List<PedidoProdutoEntity> buscaPorIdProduto(Integer idProduto);
}
