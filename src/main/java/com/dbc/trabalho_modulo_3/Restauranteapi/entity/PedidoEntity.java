package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity {
    private int idPedido;
    private Integer idCliente;
    private ClienteEntity cliente;
    private ArrayList<ProdutoEntity> produtosDoPedido;
    private double valorTotal;
    private String status;


    public void calculaValorTotal() {
        double calculaValor = 0;
        for (int i = 0; i < produtosDoPedido.size(); i++ ) {
            //calculaValor += produtosDoPedido.get(i).getValorUnitario();
        }
        setValorTotal(calculaValor);
    }


    public void adicionaProduto(ProdutoEntity produto ) {
       // this.produtosDoPedido.add(produto);

    }


    @Override
    public String toString() {
        //calculaValorTotal();
        System.out.println("++++++++++++ Itens do pedido ++++++++++++");
        if (produtosDoPedido != null) {
            for (ProdutoEntity produtos : produtosDoPedido) {
                System.out.println(produtos);
            }
        }

        return "ID do pedido: " + idPedido + " Nome do cliente: " + (cliente != null?cliente.getNome():"") + " Valor total do pedido: R$ " + valorTotal;
    }
}

