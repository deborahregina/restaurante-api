package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
    public class ContatoEntity {
        private Integer idCliente;
        private Integer idContato;
        private TipoContato tipo;
        private String telefone;
        private String descricao;


    }


