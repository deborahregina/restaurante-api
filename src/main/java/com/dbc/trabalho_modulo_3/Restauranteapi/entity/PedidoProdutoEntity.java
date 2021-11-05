package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutoEntity {

    private Integer idPedidoProduto;
    private Integer Idproduto;
    private Integer Idpedido;
    private Integer quantidade;

}
