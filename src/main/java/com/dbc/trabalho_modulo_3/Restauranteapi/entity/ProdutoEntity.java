package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

    private Integer idProduto;
    private Double valorUnitario;
    private String descrição;
    private TipoProduto tipoProduto;


}
