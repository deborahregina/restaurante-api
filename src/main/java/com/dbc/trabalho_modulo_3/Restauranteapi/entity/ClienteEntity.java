package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
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

    @JsonIgnore
    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContatoEntity> contatos;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EnderecoEntity> enderecos;

    @JsonIgnore
    @OneToMany(mappedBy = "clienteEntity", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PedidoEntity> pedidos;
}
