package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {
    private Integer idCliente;
    private String cpf;
    private String nome;
    private String email;
}
