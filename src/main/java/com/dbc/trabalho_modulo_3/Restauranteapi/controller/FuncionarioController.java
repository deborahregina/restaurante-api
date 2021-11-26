package com.dbc.trabalho_modulo_3.Restauranteapi.controller;


import com.dbc.trabalho_modulo_3.Restauranteapi.dto.ProdutoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.FuncionarioEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.FuncionarioRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.FuncionarioService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/funcionarios")
@RestController
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioRepository funcionarioService;

    @GetMapping
    public ResponseEntity<?> list() throws RegraDeNegocioException {
        List<FuncionarioEntity> list = funcionarioService.findAll();
        return ResponseEntity.ok(list);
    }

//    @PostMapping
//    public ResponseEntity<FuncionarioEntity> save(@RequestBody FuncionarioEntity funcionarioEntity){
//        FuncionarioEntity funcionarioNovo = funcionarioService.save(funcionarioEntity);
//        return funcionarioNovo;
//    }
//
//    @GetMapping(path="/{cnpj}")
//    public ResponseEntity<Cliente> view(@PathVariable String cnpj){
//
//        Cliente cliente = repository.findOne(cnpj);
//
//        if(Objects.isNull(cliente)){
//
//            return ResponseEntity.noContent().build();
//        }
//        else{
//            return ResponseEntity.ok(cliente);
//        }
//    }

}
