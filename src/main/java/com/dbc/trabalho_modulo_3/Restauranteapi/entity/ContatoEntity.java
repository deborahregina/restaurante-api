package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CONTATO_CLIENTE")
public class ContatoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTATO_SEQ")
    @SequenceGenerator(name = "CONTATO_SEQ", sequenceName = "seq_contato", allocationSize = 1)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_contato")
    private Integer idContato;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo")
    private TipoContato tipoContato;

    @Column(name = "numero")
    private String numero;

    @Column(name = "descricao")
    private String descricao;

    }


