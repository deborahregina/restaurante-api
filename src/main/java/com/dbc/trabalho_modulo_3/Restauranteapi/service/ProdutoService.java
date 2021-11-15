package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.PedidoProdutoRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ObjectMapper objectMapper;
    private final ProdutoRepository produtoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

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
        if (produtoCreateDTO.getValorUnitario() == BigDecimal.ZERO) {
            throw new RegraDeNegocioException("Valor unitário deve ser maior que zero.");
        }
        ProdutoEntity produtoCriado = produtoRepository.save(produtoEntity);
        ProdutoDTO produtoDTO = objectMapper.convertValue(produtoCriado,ProdutoDTO.class);
        return produtoDTO;
    }

    public ProdutoDTO getById(Integer idProduto) throws RegraDeNegocioException {
        ProdutoEntity produto = produtoRepository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("produto não encontrado"));
        ProdutoDTO produtoDTO = objectMapper.convertValue(produto,ProdutoDTO.class);
        return produtoDTO;
    }

    public ProdutoDTO update(Integer idProduto, ProdutoCreateDTO produtoCreateDTO) throws RegraDeNegocioException {

        produtoRepository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado"));
        if (produtoCreateDTO.getValorUnitario() == BigDecimal.ZERO) {
            throw new RegraDeNegocioException("Valor unitário deve ser maior que zero.");
        }
        ProdutoEntity produtoEntity = objectMapper.convertValue(produtoCreateDTO, ProdutoEntity.class);
        produtoEntity.setIdProduto(idProduto);
        ProdutoEntity produtoAtualizado = produtoRepository.save(produtoEntity);
        ProdutoDTO produtoDTO = objectMapper.convertValue(produtoAtualizado, ProdutoDTO.class);

        return produtoDTO;
    }

    public void delete(Integer idProduto) throws Exception {
        ProdutoEntity produtoEntity = produtoRepository.findById(idProduto).orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado!"));


        List<PedidoProdutoEntity> listaQuery = pedidoProdutoRepository.buscaPorIdProduto(idProduto);
        if (!listaQuery.isEmpty()) {
            for (PedidoProdutoEntity pedidoProduto : listaQuery) {
                pedidoProdutoRepository.delete(pedidoProduto);
            }
        }
        produtoRepository.delete(produtoEntity);
    }

    public Page<ProdutoDTO> findByTipoProduto(TipoProduto tipoProduto, Pageable pageable) {
        Page<ProdutoDTO> entities =
                produtoRepository.findByTipoProduto(tipoProduto,pageable)
                        .map(produtoEntity -> objectMapper.convertValue(produtoEntity,ProdutoDTO.class));
        return entities;
    }

}
