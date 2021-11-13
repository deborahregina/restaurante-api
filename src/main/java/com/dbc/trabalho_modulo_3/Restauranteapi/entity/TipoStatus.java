package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum TipoStatus {

    ABERTO(1),
    ARQUIVADO(2);

    private Integer tipo;

    public static TipoEndereco ofTipo(Integer tipo) {
        return Arrays.stream(TipoEndereco.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();

    }
}


