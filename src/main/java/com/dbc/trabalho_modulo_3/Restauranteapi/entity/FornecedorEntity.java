package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Document(collection = "fornecedores")
public class FornecedorEntity {

    @Id
    private String id;
    @NotEmpty
    @NotBlank
    private String nome;

    @NotEmpty
    @NotNull
    @Size(max = 8,min = 8,message = "mais de 8 caracteres")
    private String cep;

    @NotEmpty
    @NotNull
    @Size(max = 8,message = "menos de 8 caracteres")
    private String numero;
}
