package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Atendente extends Funcionario {


    @Override
    public String toString() {
        return super.toString() + "\nFunção: Atendente";
    }



}
