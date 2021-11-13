package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
//@OnDelete(action=OnDeleteAction.NO_ACTION)
@Entity(name = "PRODUTO")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUTO")
    @SequenceGenerator(name = "SEQ_PRODUTO", sequenceName = "SEQ_PRODUTO", allocationSize = 1)
    @Column(name = "ID_PRODUTO")
    private Integer idProduto;

    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TIPO_PRODUTO")
    private TipoProduto tipoProduto;

    @JsonIgnore
    @OneToMany(mappedBy = "produtoEntity", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<PedidoProdutoEntity> listaProdutoEntity;

}
