package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PEDIDO")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PEDIDO")
    @SequenceGenerator(name = "SEQ_PEDIDO", sequenceName = "SEQ_PEDIDO", allocationSize = 1)

    @Column(name = "ID_PEDIDO")
    private Integer idPedido;
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;
    private List<PedidoProdutoEntity> produtosDoPedido;
    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATA")
    private String data;


}

