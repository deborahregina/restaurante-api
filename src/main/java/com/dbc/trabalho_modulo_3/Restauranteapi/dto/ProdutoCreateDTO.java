package com.dbc.trabalho_modulo_3.Restauranteapi.dto;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoProduto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class ProdutoCreateDTO {

    @NotNull
    @ApiModelProperty(value = "Valor unitário de produto")
    @Min( value = 0L)
    private BigDecimal valorUnitario;

    @NotNull
    @NotEmpty(message = "Não pode ser vazia")
    @ApiModelProperty(value = "Descrição do produto")
    private String descricao;

    @NotNull(message = "0 ou 1")
    @ApiModelProperty(value = "Tipo de produto   0- Bebida  1- Comida")
    private TipoProduto tipoProduto;
}
