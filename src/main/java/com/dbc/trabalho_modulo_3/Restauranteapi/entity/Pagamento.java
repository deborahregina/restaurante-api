package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

public interface Pagamento {
    public boolean pagar(double valorPago, double valorTroco, double valorDoPedido);
    public double calculaTroco(PedidoEntity pedido, double valor);
}
