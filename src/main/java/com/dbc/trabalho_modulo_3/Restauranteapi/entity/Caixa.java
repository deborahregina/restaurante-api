package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Caixa implements Pagamento {

    private Atendente atendenteResponsável;
    private double valorDeCaixa;

    // O caixa precisa de um responsável, e de um valor inicial para começar as vendas.

    public Caixa(Atendente atendenteResponsável, double valorDeCaixa) {
        this.atendenteResponsável = atendenteResponsável;
        this.valorDeCaixa = valorDeCaixa;
    }

    public Atendente getAtendenteResponsável() {
        return atendenteResponsável;
    }

    public void setAtendenteResponsável(Atendente atendenteResponsável) {
        this.atendenteResponsável = atendenteResponsável;
    }

    public double getValorDeCaixa() {
        return valorDeCaixa;
    }

    public void setValorDeCaixa(double valorDeCaixa) {
        this.valorDeCaixa = valorDeCaixa;
    }

    //remover valor do caixa
    @Override
    public boolean pagar(double valorPagamento, double valorTroco,double valorDoPedido) {
        if (valorPagamento < valorDoPedido){
            System.out.println("Pagamento não realizado");
            return false;
        } else {
            double consumo = valorPagamento - valorTroco;
            setValorDeCaixa(getValorDeCaixa() + consumo);
            System.out.println("Valor do que ficou no caixa R$" + getValorDeCaixa());
        }
        return true;
    }


    @Override
    public double calculaTroco(PedidoEntity pedido, double valorPago) {
        double troco = 0;
        if (pedido.getValorTotal() > valorPago){
            System.out.println("o valor pago deve ser maior ou igual ao valor do pedido");
        } else if (valorPago == pedido.getValorTotal()){
            System.out.println("Pagamento realizado com sucesso");
            //excluir o pedido da fila
        }else if (valorPago > pedido.getValorTotal()){
            troco = valorPago - pedido.getValorTotal();
        }
        System.out.println("Valor do troco: " + troco);
        return troco;

    }

    @Override
    public String toString() {
        return "Responsável pelo caixa: " + atendenteResponsável.getNome() + " Valor em caixa: R$ " + valorDeCaixa;
    }
}


