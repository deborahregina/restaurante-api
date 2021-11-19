package com.dbc.trabalho_modulo_3.Restauranteapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;


@Data
public class PedidoProdutoDTO {

    @ApiModelProperty(value = "id produto")
    private Integer IdProduto;


    @Min(value = 0, message = "Deve ser maior que zero")
    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

}
