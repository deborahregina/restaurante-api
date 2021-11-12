package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.EnderecoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ObjectMapper objectMapper;
    private final ProdutoRepository produtoRepository;

    public List<ProdutoDTO> list(Integer idProduto) throws RegraDeNegocioException {

        if (idProduto == null) {
            return produtoRepository.findAll().stream()
                    .map(produto -> objectMapper.convertValue(produto, ProdutoDTO.class))
                    .collect(Collectors.toList());
        }

        List<ProdutoDTO> listaProduto = new ArrayList<>();

        ProdutoEntity produtoEntity = produtoRepository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));
        ProdutoDTO produtoDTO = objectMapper.convertValue(produtoEntity, ProdutoDTO.class);
        listaProduto.add(produtoDTO);
        return listaProduto;

    }

    public ProdutoDTO create(ProdutoCreateDTO produtoCreateDTO) throws RegraDeNegocioException {

        ProdutoEntity produtoEntity = objectMapper.convertValue(produtoCreateDTO, ProdutoEntity.class);
        ProdutoEntity produtoCriado = produtoRepository.save(produtoEntity);
        ProdutoDTO produtoDTO = objectMapper.convertValue(produtoCriado,ProdutoDTO.class);
        return produtoDTO;
    }

    public ProdutoDTO getById(Integer idProduto) throws RegraDeNegocioException {
        ProdutoEntity produto = produtoRepository.getById(idProduto);
        ProdutoDTO produtoDTO = objectMapper.convertValue(produto,ProdutoDTO.class);
        return produtoDTO;
    }

    public ProdutoDTO update(Integer idProduto, ProdutoCreateDTO produtoCreateDTO) throws RegraDeNegocioException {

        ProdutoEntity produtoEntity = objectMapper.convertValue(produtoCreateDTO, ProdutoEntity.class);
        produtoEntity.setIdProduto(idProduto);
        ProdutoEntity produtoAtualizado = produtoRepository.save(produtoEntity);
        ProdutoDTO produtoDTO = objectMapper.convertValue(produtoAtualizado, ProdutoDTO.class);

        return produtoDTO;
    }

    public void delete(Integer idProduto) throws Exception {
        ProdutoEntity produtoEntity = produtoRepository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));
        produtoRepository.delete(produtoEntity);
    }

}
