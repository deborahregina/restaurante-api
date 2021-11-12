package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoProduto {

    COMIDA(1),
    BEBIDA(2);

    private Integer tipo;
    public static TipoEndereco ofTipo(Integer tipo) {
        return Arrays.stream(TipoEndereco.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();

    }
}
