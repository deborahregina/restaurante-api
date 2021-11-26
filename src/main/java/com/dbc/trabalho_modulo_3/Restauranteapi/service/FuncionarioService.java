package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FuncionarioEntity;
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

    public List<FuncionarioEntity> findAll() {
        return funcionarioRepository.findAll();
    }
//
//    public long count() {
//        return arquivoRepository.count();
//    }
//
//    public Arquivo findById(String id) {
//        return arquivoRepository.findOne(id);
//    }
//
//    public void delete(String id) {
//        arquivoRepository.delete(id);
//    }

}