package com.dbc.trabalho_modulo_3.Restauranteapi.repository;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FuncionarioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends MongoRepository<FuncionarioEntity, String> {

}
