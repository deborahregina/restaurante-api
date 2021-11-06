package com.dbc.trabalho_modulo_3.Restauranteapi.repository;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class PedidoRepository {

    private List<PedidoEntity> listaPedidos = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();


    public PedidoEntity create(PedidoEntity pedidoEntity) {
        pedidoEntity.setIdPedido(COUNTER.incrementAndGet());
        listaPedidos.add(pedidoEntity);
        return pedidoEntity;
    }

    public PedidoEntity getByID(Integer idPedido) throws RegraDeNegocioException {
        PedidoEntity pedidoEntityRecuperado = listaPedidos.stream()
                .filter(pedidoEntity -> pedidoEntity.getIdPedido().equals(idPedido))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pedido não econtrado"));
        return pedidoEntityRecuperado;
    }

    public void delete(Integer idPedido) throws RegraDeNegocioException {
        PedidoEntity pedidoEntityRecuperado = listaPedidos.stream()
                .filter(pedidoEntity -> pedidoEntity.getIdPedido().equals(idPedido))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pedido não econtrado"));

        listaPedidos.remove(pedidoEntityRecuperado);
    }

    public List<PedidoEntity> list() {
        return listaPedidos;
    }

    public PedidoEntity update(Integer idPedido, PedidoEntity pedido) throws RegraDeNegocioException {

        PedidoEntity pedidoEntityRecuperado = listaPedidos.stream()
                .filter(pedidoEntity -> pedidoEntity.getIdPedido().equals(idPedido))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pedido não econtrado"));

        pedidoEntityRecuperado.setProdutosDoPedido(pedido.getProdutosDoPedido());
        pedidoEntityRecuperado.setIdCliente(pedido.getIdCliente());
        pedidoEntityRecuperado.setValorTotal(pedido.getValorTotal());
        pedidoEntityRecuperado.setStatus(pedido.getStatus());

        return pedidoEntityRecuperado;

    }

    public PedidoEntity listaByPessoa(Integer idPessoa) throws RegraDeNegocioException {

        PedidoEntity pedidoEntityRecuperado = listaPedidos.stream()
                .filter(pedidoEntity -> pedidoEntity.getIdCliente().equals(idPessoa))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Pedido não econtrado"));

        return pedidoEntityRecuperado;
    }

}
