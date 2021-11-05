package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import lombok.Data;

import javax.validation.constraints.Min;


@Data
public class PedidoProdutoDTO {

    private Integer Idproduto;

    @Min(1)
    private Integer quantidade;

}
