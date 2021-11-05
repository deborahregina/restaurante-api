package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEntity {

    private Integer idProduto;
    private Double valorUnitario;
    private String descrição;
    private TipoProduto tipoProduto;


}
