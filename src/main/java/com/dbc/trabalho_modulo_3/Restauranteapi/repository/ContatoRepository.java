package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<ContatoEntity, Integer>{
}
