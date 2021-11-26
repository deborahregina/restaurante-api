package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FuncionarioEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioEntity save(FuncionarioEntity funcionarioEntity) {
        return funcionarioRepository.save(funcionarioEntity);
    }

    public FuncionarioEntity update(String idFuncionario, FuncionarioEntity funcionarioEntity) throws RegraDeNegocioException {
        findById(idFuncionario);
        funcionarioEntity.setId(idFuncionario);
        return funcionarioRepository.save(funcionarioEntity);
    }

    public List<FuncionarioEntity> findAll() {
        return funcionarioRepository.findAll();
    }

    public void delete(String id) throws RegraDeNegocioException {
        findById(id);
        funcionarioRepository.deleteById(id);
    }

    private FuncionarioEntity findById(String id) throws RegraDeNegocioException {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Id não existe!"));
    }

}