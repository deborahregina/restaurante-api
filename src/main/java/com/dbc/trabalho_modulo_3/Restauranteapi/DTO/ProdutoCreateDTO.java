package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoProduto;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProdutoCreateDTO {

    @NotNull
    private Double valorUnitario;
    @NotNull
    private String descrição;
    @NotNull
    private TipoProduto tipoProduto;
}
