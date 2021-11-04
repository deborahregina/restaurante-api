package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoEntity {
    private  Integer idCliente;
    private Integer idEndereco;
    private TipoEndereco tipo;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cep;


}
