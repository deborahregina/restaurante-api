package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoEndereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class EnderecoDTO extends EnderecoCreateDTO {
    @NotNull
    @ApiModelProperty(value = "id Endereço")
    private Integer idEndereco;

    @ApiModelProperty(value = "id Cliente")
    private Integer idCliente;

}
