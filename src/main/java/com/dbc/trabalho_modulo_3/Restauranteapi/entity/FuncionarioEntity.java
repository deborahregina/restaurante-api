package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collection = "funcionarios")
public class FuncionarioEntity {
    @Id
    private String id;

    private String nome;
    private String cargo;
    private String telefone;
    private String cep;
//    private Date dataNascimento;

}
