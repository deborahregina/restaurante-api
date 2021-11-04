package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClienteDTO extends ClienteCreateDTO{
    @NotNull
    @ApiModelProperty(value = "id Cliente")
    private Integer idCliente;
}
