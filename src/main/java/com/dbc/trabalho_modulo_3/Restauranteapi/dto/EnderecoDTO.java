package com.dbc.trabalho_modulo_3.Restauranteapi.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EnderecoDTO extends EnderecoCreateDTO {
    @NotNull
    @ApiModelProperty(value = "id Endereço")
    private Integer idEndereco;

    @ApiModelProperty(value = "id Cliente")
    private Integer idCliente;

}
