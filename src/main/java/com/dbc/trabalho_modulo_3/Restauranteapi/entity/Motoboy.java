package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motoboy extends Funcionario {
    private String placaMoto;


    @Override
    public String toString() {
        return super.toString()+ "Motoboy";
    }


}
