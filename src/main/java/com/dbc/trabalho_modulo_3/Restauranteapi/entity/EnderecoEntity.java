package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;

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
    @Column(name = "id_endereco")
    private Integer idEndereco;


    @Column(name = "tipo")
    @Enumerated(EnumType.ORDINAL)
    private TipoEndereco tipoEndereco;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String cep;

    @Column (name = "Bairro")
    private String bairro;


}
