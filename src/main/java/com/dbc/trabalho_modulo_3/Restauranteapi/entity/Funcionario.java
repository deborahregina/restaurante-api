package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class Funcionario {
    private int idFuncionario;
    private String nome;
    private double salario;

}
