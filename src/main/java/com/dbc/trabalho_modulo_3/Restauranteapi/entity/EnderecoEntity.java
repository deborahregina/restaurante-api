package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity(name = "ENDERECO_CLIENTE")
public class EnderecoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ENDERECO_CLIENTE")
    @SequenceGenerator(name = "SEQ_ENDERECO_CLIENTE", sequenceName = "SEQ_ENDERECO_CLIENTE", allocationSize = 1)
    @Column(name = "ID_ENDERECO")
    private Integer idEndereco;


    @Column(name = "TIPO")
    @Enumerated(EnumType.ORDINAL)
    private TipoEndereco tipoEndereco;

    @Column(name = "LOGRADOURO")
    private String logradouro;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "CEP")
    private String cep;

    @Column (name = "BAIRRO")
    private String bairro;


}
