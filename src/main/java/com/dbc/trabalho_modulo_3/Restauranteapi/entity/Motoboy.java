package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Motoboy extends Funcionario {
    private String placaMoto;


    @Override
    public String toString() {
        return super.toString()+ "Motoboy";
    }


}
