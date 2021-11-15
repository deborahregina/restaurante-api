package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@Data
public class PedidoProdutoDTO {

    @ApiModelProperty(value = "id produto")
    private Integer IdProduto;

    @Valid
    @Min(value = 1, message = "Deve ser maior que zero")
    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

}
