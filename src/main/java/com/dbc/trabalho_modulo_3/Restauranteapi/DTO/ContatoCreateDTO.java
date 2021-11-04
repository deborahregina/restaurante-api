package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoContato;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class ContatoCreateDTO {

    @NotNull
    @ApiModelProperty(value = "Id Pessoa")
    private Integer IdCliente;

    @NotNull
    @ApiModelProperty(value = "Tipo de contato")
    private TipoContato tipo;

    @NotEmpty
    @NotNull
    @Size(max = 13, message = "numero maior que 13 digitos")
    @ApiModelProperty(value = "Número telefone")
    private String telefone;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Descrição")
    private String descricao;
}
