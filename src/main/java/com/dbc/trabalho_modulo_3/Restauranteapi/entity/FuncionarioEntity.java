package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
