package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    private Integer idPedido;
    private Integer idCliente;
    private List<PedidoProdutoEntity> produtosDoPedido;
    private Double valorTotal;
    private String status;


}

