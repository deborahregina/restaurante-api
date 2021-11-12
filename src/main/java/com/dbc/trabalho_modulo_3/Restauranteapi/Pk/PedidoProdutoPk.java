package com.dbc.trabalho_modulo_3.Restauranteapi.Pk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutoPk implements Serializable {

    @Column(name = "id_produto")
    private Integer idProduto;

    @Column(name = "id_pedido")
    private Integer idPedido;
}
