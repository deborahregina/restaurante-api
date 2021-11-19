package com.dbc.trabalho_modulo_3.Restauranteapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContatoDTO extends ContatoCreateDTO{
    @NotNull
    @ApiModelProperty(value = "id Contato")
    private Integer idContato;

    private Integer idCliente;

}
