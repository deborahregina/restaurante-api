package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FornecedorEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FuncionarioEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public FornecedorEntity save(FornecedorEntity fornecedorEntity) {
        return fornecedorRepository.save(fornecedorEntity);
    }

    public FornecedorEntity update(String idFuncionario, FornecedorEntity fornecedorEntity) throws RegraDeNegocioException {
        findById(idFuncionario);
        fornecedorEntity.setId(idFuncionario);
        return fornecedorRepository.save(fornecedorEntity);
    }

    public List<FornecedorEntity> findAll() {
        return fornecedorRepository.findAll();
    }

    public void delete(String id) throws RegraDeNegocioException {
        findById(id);
        fornecedorRepository.deleteById(id);
    }

    private FornecedorEntity findById(String id) throws RegraDeNegocioException {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Id não existe!"));
    }
}
