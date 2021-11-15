package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ClienteCreateDTO {
    @NotNull
    @NotEmpty
    @Size(max = 11, min = 11,message = "Deve ter 11 caracteres")
    @ApiModelProperty(value = "CPF")
    private String cpf;
    @NotEmpty
    @NotNull
    @ApiModelProperty(value = "Nome do Cliente")
    private String nome;
    @NotNull(message = "Não pode ser nulo")
    @ApiModelProperty(value = "E-mai")
    @Email
    private String email;

}
