package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProdutoDTO extends ProdutoCreateDTO{

    @ApiModelProperty(value = "id produto")
    private Integer idProduto;
}
