package com.dbc.trabalho_modulo_3.Restauranteapi.controller;


import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.EnderecoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/produto")
@Validated
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/{idProduto}")
    @ApiOperation(value = "Lista produto por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto listado com sucesso"),
            @ApiResponse(code = 400, message = "Produto não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ProdutoDTO listByID(@PathVariable("idProduto") Integer idProduto) throws RegraDeNegocioException {
        return produtoService.getById(idProduto);
    }

    @PostMapping
    @ApiOperation(value = "Cria novo produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto criado com sucesso"),
            @ApiResponse(code = 400, message = "Dados do produto inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ProdutoDTO create(@RequestBody @Valid ProdutoCreateDTO produtoCreateDTO) throws RegraDeNegocioException {
        ProdutoDTO produtoEntityCriado = produtoService.create(produtoCreateDTO);
        return produtoEntityCriado;
    }

    @PutMapping("/{idProduto}")
    @ApiOperation(value = "Altera produto por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produto alterado com sucesso"),
            @ApiResponse(code = 400, message = "Dados do produto inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ProdutoDTO update(@PathVariable("idProduto") Integer idProduto,
                              @Valid @RequestBody ProdutoCreateDTO produtoCreateDTO) throws Exception {
        ProdutoDTO produtoDTO = produtoService.update(idProduto, produtoCreateDTO);
        return produtoDTO;
    }


    @GetMapping
    @ApiOperation(value = "Lista produtos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos listados com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<ProdutoDTO> list() {
        return produtoService.list();
    }

    @DeleteMapping("/{idProduto}")
    public void delete(@PathVariable("idProduto") Integer idProduto) throws Exception {
        produtoService.delete(idProduto);
    }
}
