package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ClienteDTO extends ClienteCreateDTO{
    @NotNull
    @ApiModelProperty(value = "id Cliente")
    private Integer idCliente;

    List<ContatoDTO> contatos;

    List<EnderecoDTO> enderecos;
}
