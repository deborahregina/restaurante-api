package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TipoContato {
    RESIDENCIAL(1),
    COMERCIAL(2);

    private Integer tipo;

    public static TipoContato ofTipo(Integer tipo){
        return Arrays.stream(TipoContato.values())
                .filter(tp -> tp.getTipo().equals(tipo))
                .findFirst()
                .get();
    }

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
