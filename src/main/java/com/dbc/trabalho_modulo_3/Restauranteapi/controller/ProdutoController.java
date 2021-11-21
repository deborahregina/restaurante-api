package com.dbc.trabalho_modulo_3.Restauranteapi.controller;


import com.dbc.trabalho_modulo_3.Restauranteapi.dto.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoProduto;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return produtoService.update(idProduto, produtoCreateDTO);
    }


    @GetMapping("/idProduto")
    @ApiOperation(value = "Lista os produtos pelo id do produto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos listados com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<ProdutoDTO> list(@RequestParam(required = false) Integer idProduto) throws RegraDeNegocioException {
        return produtoService.list(idProduto);
    }

    @DeleteMapping("/{idProduto}")
    @ApiOperation(value = "Exclui o produto pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Produtos listados com sucesso"),
            @ApiResponse(code = 400, message = "Produto não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public void delete(@PathVariable("idProduto") Integer idProduto) throws Exception {
        produtoService.delete(idProduto);
    }

    @GetMapping("/lista-por-tipoProduto")
    @ApiOperation(value = "Lista por tipo de produto")
    public Page<ProdutoDTO> findByTipoProduto(
            @RequestParam Integer pagina,
            @RequestParam Integer quantidadeDeRegistrosPorPagina, @RequestParam TipoProduto tipoProduto) throws RegraDeNegocioException {
        Pageable pageable = PageRequest.of(pagina,
                quantidadeDeRegistrosPorPagina,
                Sort.by("descricao"));
        Page<ProdutoDTO> paginaDoBanco = produtoService.findByTipoProduto(tipoProduto, pageable);
        return paginaDoBanco;
    }
}
