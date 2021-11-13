package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PEDIDO_PRODUTO")
public class PedidoProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PEDIDO_PRODUTO")
    @SequenceGenerator(name = "SEQ_PEDIDO_PRODUTO", sequenceName = "SEQ_PEDIDO_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PEDIDO_PRODUTO")
    private Integer idPedidoProduto;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    private PedidoEntity pedidoEntity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private ProdutoEntity produtoEntity;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
