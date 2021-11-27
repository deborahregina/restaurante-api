package com.dbc.trabalho_modulo_3.Restauranteapi.controller;


import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FornecedorEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FuncionarioEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.FornecedorService;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.FuncionarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/fornecedores")
@RestController
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping
    public List<FornecedorEntity> list() throws RegraDeNegocioException {
        return fornecedorService.findAll();
    }

    @PostMapping
    public FornecedorEntity save(@RequestBody FornecedorEntity fornecedorEntity){
        return fornecedorService.save(fornecedorEntity);
    }

    @PutMapping("/idFornecedor")
    public FornecedorEntity update(@RequestParam("idFuncionario") String idFornecedor
            , @RequestBody FornecedorEntity fornecedorEntity) throws RegraDeNegocioException {
        return fornecedorService.update(idFornecedor,fornecedorEntity);
    }

    @DeleteMapping("/idFornecedor")
    public void delete(@RequestParam("idFornecedor") String idFornecedor) throws RegraDeNegocioException {
        fornecedorService.delete(idFornecedor);
    }
}
