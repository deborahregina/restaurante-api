package com.dbc.trabalho_modulo_3.Restauranteapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoDTO extends PedidoCreateDTO {

    @ApiModelProperty(value = "id Cliente")
    private Integer idCliente;

    @ApiModelProperty(value = "id Pedido")
    private Integer idPedido;

    @ApiModelProperty(value = "Valor total do pedido")
    private BigDecimal valorTotal;

    @ApiModelProperty(value = "Data de criação do pedido")
    private LocalDateTime data;

}
