package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
    public class ContatoEntity {
        private Integer idCliente;
        private Integer idContato;
        private TipoContato tipo;
        private String telefone;
        private String descricao;


    }


