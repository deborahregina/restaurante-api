package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntity,Integer> {

    @Query("select pp from pedido_produto where pp.id_produto = :idProduto AND pp.id_pedido = :idPedido")
    PedidoProdutoEntity buscaPorProduto(Integer idProduto,Integer idPedido);
}
