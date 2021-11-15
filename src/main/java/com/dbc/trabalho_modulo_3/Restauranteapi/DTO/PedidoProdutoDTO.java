package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@Data
public class PedidoProdutoDTO {

    @ApiModelProperty(value = "id produto")
    private Integer IdProduto;


    @Min(value = 0, message = "Deve ser maior que zero")
    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

}
