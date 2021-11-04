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

    private int idProduto;
    private double valorUnitario;
    private String descrição;
    private TipoProduto tipoProduto;


    @Override
    public String toString() {
        String tipoProdutoEh = "";
        if (tipoProduto.getTipo() == 1) {
            tipoProdutoEh = "Comida";
        }
        if (tipoProduto.getTipo() == 2) {
            tipoProdutoEh = "Bebida";
        }
        return "ID do produto: " + idProduto +  " | Tipo do produto: " + tipoProdutoEh +" | Nome do produto: " + descrição+ " | Valor unitário: R$ " + valorUnitario +  "\n";
    }

}
