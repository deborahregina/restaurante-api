package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProduto {
    private ProdutoEntity produto;
    private PedidoEntity pedido;
    private Integer quantidade;



    @Override
    public String toString() {
        return "Pedidos em aberto{" +
                "id pedido = " + pedido.getIdPedido() +
                ", id produto = " + produto.getIdProduto() +
                ", nome produto = " + produto.getDescrição() +
                ", quantidade = " + quantidade +
                ", preço = " + produto.getValorUnitario() +
                ", status = " + pedido.getStatus() +
                '}';
    }
}
