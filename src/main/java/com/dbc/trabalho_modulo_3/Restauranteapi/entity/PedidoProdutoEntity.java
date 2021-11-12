package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import com.dbc.trabalho_modulo_3.Restauranteapi.Pk.PedidoProdutoPk;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PEDIDO_PRODUTO")
public class PedidoProdutoEntity {

    @EmbeddedId
    private PedidoProdutoPk pedidoProdutoPk;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
