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


import java.util.List;

@RequestMapping("/funcionarios")
@RestController
@RequiredArgsConstructor
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    @GetMapping
    public List<FuncionarioEntity> list() throws RegraDeNegocioException {
        return funcionarioService.findAll();
    }

    @PostMapping
    public FuncionarioEntity save(@RequestBody FuncionarioEntity funcionarioEntity){
        return funcionarioService.save(funcionarioEntity);
    }

    @PutMapping("/idFuncionario")
    public FuncionarioEntity update(@RequestParam("idFuncionario") String idFuncionario
            ,@RequestBody FuncionarioEntity funcionarioEntity) throws RegraDeNegocioException {
        return funcionarioService.update(idFuncionario,funcionarioEntity);
    }

    @DeleteMapping("/idFuncionario")
    public void delete(@RequestParam("idFuncionario") String idFuncionario) throws RegraDeNegocioException {
        funcionarioService.delete(idFuncionario);
    }

}
