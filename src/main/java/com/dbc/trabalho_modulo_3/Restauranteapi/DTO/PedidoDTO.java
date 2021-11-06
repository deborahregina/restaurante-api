package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PedidoDTO extends PedidoCreateDTO {

    @ApiModelProperty(value = "id Pedido")
    private Integer idPedido;

    @NotNull
    @ApiModelProperty(value = "Status do pedido. Ex: Aberto, Fechado")
    private String status;

    @ApiModelProperty(value = "Valor total do pedido")
    private BigDecimal valorTotal;


}
