package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class PedidoCreateDTO {

    @ApiModelProperty(value = "id Cliente")
    private Integer idCliente;

    @ApiModelProperty(value = "Lista de Id de produtos e quantidade")
    private List<PedidoProdutoDTO> pedidoProduto;

    @ApiModelProperty(value = "Status do pedido. Ex: Aberto, Fechado")
    private String status;

}
