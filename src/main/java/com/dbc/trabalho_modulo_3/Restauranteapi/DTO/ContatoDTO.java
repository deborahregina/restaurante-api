package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContatoDTO extends ContatoCreateDTO{
    @NotNull
    @ApiModelProperty(value = "Tipo de contato")
    private Integer idContato;
}
