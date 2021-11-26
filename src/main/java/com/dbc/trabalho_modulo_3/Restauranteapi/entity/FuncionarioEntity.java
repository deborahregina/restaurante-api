package com.dbc.trabalho_modulo_3.Restauranteapi.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Document(collection = "funcionarios")
public class FuncionarioEntity {
    @Id
    private String id;
    @NotEmpty
    @NotBlank
    private String nome;
    @NotEmpty
    @NotBlank
    private String cargo;
    @NotNull
    @NotEmpty
    @Size(max = 13, message = "Não pode possuir mais que 13 caracteres")
    private String telefone;
    @NotEmpty
    @NotNull
    @Size(max = 8,min = 8,message = "mais de 8 caracteres")
    private String cep;
//    private Date dataNascimento;

}
