package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    @Query(value = "SELECT * FROM PEDIDO p WHERE p.id_cliente = :idCliente", nativeQuery = true)
    List<PedidoEntity> buscaPedidosPorIdCliente(Integer idCliente);

    @Query("select p " +
            " from PEDIDO p " +
            "where status = :status ")
    Page<PedidoEntity> findByStatus(TipoStatus status, Pageable pageable);

    @Query("select p" + " from PEDIDO p " + " where status = :status")
    List<PedidoEntity> findPedidosByStatus(TipoStatus status);

    //SELECT sum(p.valor_total) FROM pedido p WHERE CAST(p.data_pedido AS DATE) = '2021-12-02'
    @Query(value = "SELECT sum(p.valor_total) FROM pedido p WHERE CAST(p.data_pedido AS DATE) = cast(current_date as DATE)", nativeQuery = true)
    BigDecimal somaDosPedidos();

}
