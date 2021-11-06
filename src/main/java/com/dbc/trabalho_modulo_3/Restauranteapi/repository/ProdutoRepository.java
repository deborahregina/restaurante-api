package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.EnderecoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoEndereco;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoProduto;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProdutoRepository {

    private static List<ProdutoEntity> listaProdutoEntities = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public ProdutoRepository() {
        listaProdutoEntities.add(new ProdutoEntity(COUNTER.incrementAndGet(), 10.50,"Heineken Long Neck",TipoProduto.BEBIDA));
        listaProdutoEntities.add(new ProdutoEntity(COUNTER.incrementAndGet(), 7.50,"Coca-cola 2L",TipoProduto.BEBIDA));
        listaProdutoEntities.add(new ProdutoEntity(COUNTER.incrementAndGet(), 20.50,"Pizza brotinho de chocolate",TipoProduto.COMIDA));
        listaProdutoEntities.add(new ProdutoEntity(COUNTER.incrementAndGet(), 20.50,"Pizza brotinho de calabresa",TipoProduto.COMIDA));

    }

    public List<ProdutoEntity> list() {
        return listaProdutoEntities;
    }

    public ProdutoEntity create(ProdutoEntity produtoEntity) {
        produtoEntity.setIdProduto(COUNTER.incrementAndGet());
        listaProdutoEntities.add(produtoEntity);
        return produtoEntity;
    }

    public ProdutoEntity getById(Integer idProduto) throws RegraDeNegocioException {
        return listaProdutoEntities.stream()
                .filter(produto -> produto.getIdProduto().equals(idProduto)).findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado"));
    }

    public ProdutoEntity update(Integer idProduto, ProdutoEntity produto) throws RegraDeNegocioException {

        ProdutoEntity produtoRecuperado = listaProdutoEntities.stream()
                .filter(produtoAt -> produtoAt.getIdProduto().equals(idProduto))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado"));
        produtoRecuperado.setTipoProduto(produto.getTipoProduto());
        produtoRecuperado.setDescrição(produto.getDescrição());
        produtoRecuperado.setValorUnitario(produto.getValorUnitario());

        return produtoRecuperado;
    }

    public void delete(Integer idProduto) throws Exception {
        ProdutoEntity produtobackup = listaProdutoEntities.stream()
                .filter(produtoEntity -> produtoEntity.getIdProduto().equals(idProduto))
                .findFirst()
                .orElseThrow(() -> new Exception("produto não localizado"));
        listaProdutoEntities.remove(produtobackup);
    }
}
