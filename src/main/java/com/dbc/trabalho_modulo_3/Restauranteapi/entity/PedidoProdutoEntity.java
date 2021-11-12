package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PEDIDO_PRODUTO")
public class PedidoProdutoEntity {

    private Integer idPedidoProduto;
    private Integer Idproduto;
    private Integer Idpedido;
    private Integer quantidade;

}
