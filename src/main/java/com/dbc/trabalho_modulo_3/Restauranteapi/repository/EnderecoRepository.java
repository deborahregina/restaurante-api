package com.dbc.trabalho_modulo_3.Restauranteapi.repository;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.EnderecoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Integer> {

}

