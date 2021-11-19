package com.dbc.trabalho_modulo_3.Restauranteapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProdutoDTO extends ProdutoCreateDTO{

    @ApiModelProperty(value = "id produto")
    private Integer idProduto;
}
