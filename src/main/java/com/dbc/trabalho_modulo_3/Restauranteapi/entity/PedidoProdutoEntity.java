package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PEDIDO_PRODUTO")
public class PedidoProdutoEntity {


    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "ID_PEDIDO")
    private Integer Idpedido;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
