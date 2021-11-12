package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CLIENTE")
public class ClienteEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
    @SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "SEQ_CLIENTE", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;

    @Column(name = "CPF")
    private String cpf;


    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.LAZY)
    private Set<ContatoEntity> contatos;

    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.LAZY)
    private Set<EnderecoEntity> enderecos;
}
