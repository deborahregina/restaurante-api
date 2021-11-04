package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoEndereco;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class EnderecoDTO {

    private Integer idCliente;
    private Integer idEndereco;
    @NotNull
    private TipoEndereco tipo;
    @NotEmpty
    @NotNull
    private String logradouro;
    @NotNull
    private int numero;
    private String bairro;
    @NotEmpty
    @NotNull
    @Size(max = 8,min = 8,message = "mais de 8 caracteres")
    private String cep;
}
