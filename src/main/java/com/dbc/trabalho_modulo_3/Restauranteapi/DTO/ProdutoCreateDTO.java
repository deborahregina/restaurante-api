package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoProduto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProdutoCreateDTO {

    @NotNull
    @ApiModelProperty(value = "Valor unitário de produto")
    private BigDecimal valorUnitario;
    @NotNull
    @ApiModelProperty(value = "Descrição do produto")
    private String descrição;
    @NotNull
    @ApiModelProperty(value = "Tipo de produto   0- Bebida  1- Comida")
    private TipoProduto tipoProduto;
}
