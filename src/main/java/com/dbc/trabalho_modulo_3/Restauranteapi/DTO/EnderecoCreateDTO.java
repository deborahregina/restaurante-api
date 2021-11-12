package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoEndereco;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class EnderecoCreateDTO {


    @NotNull
    @ApiModelProperty(value = "Tipo Enderecço 0 - Residencial   1 - Comercial")
    private TipoEndereco tipoEndereco;

    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Logradouro")
    private String logradouro;

    @NotNull
    @ApiModelProperty(value = "número")
    private int numero;

    @ApiModelProperty(value = "Bairro")
    private String bairro;

    @NotEmpty
    @NotNull
    @Size(max = 8,min = 8,message = "Digite 8 caracteres")
    @ApiModelProperty(value = "cep")
    private String cep;
}
