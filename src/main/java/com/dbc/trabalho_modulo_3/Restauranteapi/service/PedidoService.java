package com.dbc.trabalho_modulo_3.Restauranteapi.service;


import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.PedidoRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ObjectMapper objectMapper;
    private final ProdutoService produtoService;
    private final EmailService emailService;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoDTO create(Integer idCliente, PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {

        for (PedidoProdutoDTO pedidoProduto : pedidoCreateDTO.getPedidoProduto()) {
            produtoService.getById(pedidoProduto.getIdProduto());
        }

        ClienteEntity cliente = clienteRepository.getById(idCliente);
        PedidoEntity pedidoEntity = objectMapper.convertValue(pedidoCreateDTO, PedidoEntity.class);

        LocalDateTime dataPedido = LocalDateTime.now();
        pedidoEntity.setData(dataPedido);
        pedidoEntity.setValorTotal(BigDecimal.ZERO);
        pedidoEntity.setClienteEntity(cliente);
        PedidoEntity pedidoCriado = pedidoRepository.save(pedidoEntity);


        Set<PedidoProdutoEntity> listaProdutos = new HashSet<>();
        for (PedidoProdutoDTO pedidoProdutoDTO : pedidoCreateDTO.getPedidoProduto()) {
            PedidoProdutoEntity pedidoProdutoEntity = objectMapper.convertValue(pedidoProdutoDTO, PedidoProdutoEntity.class);
            pedidoProdutoEntity.setProdutoEntity(produtoRepository.getById(pedidoProdutoDTO.getIdProduto()));
            pedidoProdutoEntity.setPedidoEntity(pedidoCriado);
            listaProdutos.add(pedidoProdutoEntity);
        }

        pedidoEntity.setProdutosDoPedido(listaProdutos);
        pedidoEntity.setValorTotal(calculavalorTotal(pedidoEntity));


        pedidoCriado = pedidoRepository.save(pedidoEntity);

        List<PedidoProdutoDTO> listaPedidosProdutoDTO = pedidoCriado.getProdutosDoPedido().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                .collect(Collectors.toList());


        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoCriado, PedidoDTO.class);
        pedidoDTO.setPedidoProduto(listaPedidosProdutoDTO);
        pedidoDTO.setValorTotal(pedidoCriado.getValorTotal());
        pedidoDTO.setData(dataPedido);
        pedidoDTO.setIdCliente(idCliente);


        emailService.enviarEmailComTemplate(pedidoCriado, cliente);
        return pedidoDTO;
    }

    public PedidoDTO getByID(Integer idPedido) throws RegraDeNegocioException {

        PedidoEntity pedidoEntity = pedidoRepository.findById(idPedido).orElseThrow(() -> new RegraDeNegocioException("Pessoa não encontrada"));

        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoEntity, PedidoDTO.class);
        pedidoDTO.setData(pedidoEntity.getData());

        List<PedidoProdutoDTO> listaPedidoProduto;

        listaPedidoProduto = pedidoEntity.getProdutosDoPedido().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                .collect(Collectors.toList());

        pedidoDTO.setPedidoProduto(listaPedidoProduto);
        pedidoDTO.setValorTotal(calculavalorTotal(pedidoEntity));
        return pedidoDTO;
    }

    public void delete(Integer idPedido) throws RegraDeNegocioException {
        PedidoEntity pedidoEntity = pedidoRepository.findById(idPedido).orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado!"));
        pedidoRepository.delete(pedidoEntity);
    }


    public List<PedidoDTO> list(Integer idPedido) throws RegraDeNegocioException {

        List<PedidoDTO> pedidoDTOList = new ArrayList<>();

        if (idPedido == null) {
            List<PedidoEntity> listaPedidoEntities = pedidoRepository.findAll();

            for (PedidoEntity pedidoEntity : listaPedidoEntities) {

                PedidoDTO pedidoConvertido = objectMapper.convertValue(pedidoEntity, PedidoDTO.class);

                List<PedidoProdutoDTO> pedidosProduto = pedidoEntity.getProdutosDoPedido().stream()
                        .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                        .collect(Collectors.toList());

                pedidoConvertido.setPedidoProduto(pedidosProduto);
                pedidoConvertido.setIdCliente(pedidoEntity.getClienteEntity().getIdCliente());
                pedidoConvertido.setValorTotal(calculavalorTotal(pedidoEntity));
                pedidoConvertido.setData(pedidoEntity.getData());
                pedidoDTOList.add(pedidoConvertido);
            }

            return pedidoDTOList;
        }

        PedidoEntity pedidoEntity = pedidoRepository.findById(idPedido).orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));

        List<PedidoProdutoDTO> pedidosProduto = pedidoEntity.getProdutosDoPedido().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                .collect(Collectors.toList());

        PedidoDTO pedidoConvertido = objectMapper.convertValue(pedidoEntity, PedidoDTO.class);
        pedidoConvertido.setPedidoProduto(pedidosProduto);
        pedidoConvertido.setIdCliente(pedidoEntity.getClienteEntity().getIdCliente());
        pedidoConvertido.setValorTotal(calculavalorTotal(pedidoEntity));
        pedidoConvertido.setData(pedidoEntity.getData());
        pedidoDTOList.add(pedidoConvertido);

        return pedidoDTOList;
    }

    public PedidoDTO update(Integer idPedido, PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException {

        PedidoEntity pedidoRecuperado = pedidoRepository.findById(idPedido).orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado!"));

        for (PedidoProdutoDTO pedidoProduto : pedidoCreateDTO.getPedidoProduto()) {
            produtoService.getById(pedidoProduto.getIdProduto());
        }

        Set<PedidoProdutoEntity> listaPedidoProduto = pedidoCreateDTO.getPedidoProduto().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoEntity.class))
                .collect(Collectors.toSet());


        PedidoEntity pedidoEntity = objectMapper.convertValue(pedidoCreateDTO, PedidoEntity.class);
        pedidoEntity.setProdutosDoPedido(listaPedidoProduto);
        pedidoEntity.setClienteEntity(pedidoRecuperado.getClienteEntity());
        LocalDateTime dataPedido = LocalDateTime.now();
        pedidoEntity.setData(dataPedido);
        pedidoEntity.setValorTotal(calculavalorTotal(pedidoEntity));

        PedidoEntity pedidoAtualizado = pedidoRepository.save(pedidoEntity);

        List<PedidoProdutoDTO> listaPedidosProdutoDTO = pedidoAtualizado.getProdutosDoPedido().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                .collect(Collectors.toList());

        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoAtualizado, PedidoDTO.class);
        pedidoDTO.setPedidoProduto(listaPedidosProdutoDTO);
        pedidoDTO.setValorTotal(calculavalorTotal(pedidoEntity));
        pedidoDTO.setData(pedidoAtualizado.getData());

        return pedidoDTO;
    }

    private BigDecimal calculavalorTotal(PedidoEntity pedidoEntity) throws RegraDeNegocioException {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (PedidoProdutoEntity pedidoProdutos : pedidoEntity.getProdutosDoPedido()) {
            ProdutoDTO produto = produtoService.getById(pedidoProdutos.getProdutoEntity().getIdProduto());
            BigDecimal valorUnitario = produto.getValorUnitario();
            Integer quantidade = pedidoProdutos.getQuantidade();
            valorTotal = valorTotal.add(valorUnitario.multiply(BigDecimal.valueOf(quantidade)));

        }
        return valorTotal;
    }

    public List<PedidoEntity> listaPedidoEntityPorIdCliente(Integer idCliente) {

        return pedidoRepository.findAll().stream().
                filter(pedido -> pedido.getClienteEntity().getIdCliente().equals(idCliente)).collect(Collectors.toList());

    }

}
