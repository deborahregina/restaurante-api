package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    @Query(value = "SELECT * FROM PEDIDO p WHERE p.id_cliente = :idCliente", nativeQuery = true)
    List<PedidoEntity> buscaPedidosPorIdCliente(Integer idCliente);


}
