package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUTO")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTO")
    @SequenceGenerator(name = "SEQ_PRODUTO", sequenceName = "SEQ_PRODUTO", allocationSize = 1)


    @Column(name = "ID_PRODUTO")
    private Integer idProduto;
    @Column(name = "")
    private Double valorUnitario;
    private String descrição;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TIPO_PRODUTO ")
    private TipoProduto tipoProduto;

    @JsonIgnore
    @ManyToMany(mappedBy = "produtosDoPedido")
    private List<PedidoProdutoEntity> pedidoProdutoEntityList;

}
