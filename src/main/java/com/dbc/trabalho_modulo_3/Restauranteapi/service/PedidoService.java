package com.dbc.trabalho_modulo_3.Restauranteapi.service;


import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.PedidoProdutoRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.PedidoRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private final PedidoProdutoRepository pedidoProdutoRepository;

    public PedidoDTO create(Integer idCliente, PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {

        for (PedidoProdutoDTO pedidoProduto : pedidoCreateDTO.getPedidoProduto()) {
            produtoService.getById(pedidoProduto.getIdProduto());
        }

        ClienteEntity cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
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

        emailService.enviarEmailComTemplate(pedidoCriado, cliente);

        return fromEntity(pedidoCriado);
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

        for (PedidoProdutoEntity pedidoProduto : pedidoEntity.getProdutosDoPedido()) {
            pedidoProdutoRepository.delete(pedidoProduto);
        }

        pedidoRepository.delete(pedidoEntity);
    }


    public List<PedidoDTO> list(Integer idPedido) throws RegraDeNegocioException {

        List<PedidoDTO> pedidoDTOList = new ArrayList<>();

        if (idPedido == null) {
            List<PedidoEntity> listaPedidoEntities = pedidoRepository.findAll();

            for (PedidoEntity pedidoEntity : listaPedidoEntities) {

                pedidoEntity.setValorTotal(calculavalorTotal(pedidoEntity));
                pedidoDTOList.add(fromEntity(pedidoEntity));
            }

            return pedidoDTOList;
        }

        PedidoEntity pedidoEntity = pedidoRepository.findById(idPedido).orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));

        pedidoEntity.setValorTotal(calculavalorTotal(pedidoEntity));
        pedidoDTOList.add(fromEntity(pedidoEntity));

        return pedidoDTOList;
    }

    public PedidoDTO update(Integer idPedido, PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException {

        PedidoEntity pedidoRecuperado = pedidoRepository.findById(idPedido).orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado!"));

        for (PedidoProdutoDTO pedidoProduto : pedidoCreateDTO.getPedidoProduto()) {
            produtoService.getById(pedidoProduto.getIdProduto());
        }

        for (PedidoProdutoEntity pedidoProdutoEntity : pedidoRecuperado.getProdutosDoPedido()) {
            for (PedidoProdutoDTO pedidoProdutoDTO : pedidoCreateDTO.getPedidoProduto()) {
                if (pedidoProdutoEntity.getProdutoEntity().getIdProduto() == pedidoProdutoDTO.getIdProduto()) {
                    pedidoProdutoRepository.delete(pedidoProdutoEntity);
                }
            }
        }


        PedidoEntity pedidoEntity = objectMapper.convertValue(pedidoCreateDTO, PedidoEntity.class);

        ClienteEntity cliente = pedidoRecuperado.getClienteEntity();

        LocalDateTime dataPedido = LocalDateTime.now();
        pedidoRecuperado.setData(dataPedido);
        pedidoRecuperado.setValorTotal(BigDecimal.ZERO);
        pedidoRecuperado.setClienteEntity(cliente);
        pedidoRecuperado.setIdPedido(idPedido);
        pedidoRecuperado.setProdutosDoPedido(pedidoEntity.getProdutosDoPedido());
        pedidoRecuperado.setStatus(pedidoEntity.getStatus());

        PedidoEntity pedidoMod = pedidoRepository.save(pedidoRecuperado);

        Set<PedidoProdutoEntity> listaProdutos = new HashSet<>();
        for (PedidoProdutoDTO pedidoProdutoDTO : pedidoCreateDTO.getPedidoProduto()) {
            PedidoProdutoEntity pedidoProdutoEntity = objectMapper.convertValue(pedidoProdutoDTO, PedidoProdutoEntity.class);
            pedidoProdutoEntity.setProdutoEntity(produtoRepository.getById(pedidoProdutoDTO.getIdProduto()));
            pedidoProdutoEntity.setPedidoEntity(pedidoMod);
            listaProdutos.add(pedidoProdutoEntity);
        }

        pedidoRecuperado.setProdutosDoPedido(listaProdutos);
        pedidoMod = pedidoRepository.save(pedidoRecuperado);
        pedidoRecuperado.setValorTotal(calculavalorTotal(pedidoMod));
        pedidoMod = pedidoRepository.save(pedidoRecuperado);


        return fromEntity(pedidoMod);

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


    private PedidoDTO fromEntity(PedidoEntity pedido) {

        PedidoDTO pedidoDTO = objectMapper.convertValue(pedido, PedidoDTO.class);

        pedidoDTO.setIdPedido(pedido.getIdPedido());
        pedidoDTO.setIdCliente(pedido.getClienteEntity().getIdCliente());
        pedidoDTO.setData(pedido.getData());
        Set<PedidoProdutoEntity> pedidoProdutoEntities = pedido.getProdutosDoPedido();
        List<PedidoProdutoDTO> pedidoProdutoDTOS = new ArrayList<>();

        for (PedidoProdutoEntity pedidoProduto : pedidoProdutoEntities) {
            PedidoProdutoDTO pedidoProdutoDTO = objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class);
            pedidoProdutoDTO.setIdProduto(pedidoProduto.getProdutoEntity().getIdProduto());
            pedidoProdutoDTOS.add(pedidoProdutoDTO);

        }

        pedidoDTO.setPedidoProduto(pedidoProdutoDTOS);
        pedidoDTO.setValorTotal(pedido.getValorTotal());
        return pedidoDTO;
    }

    public Page<PedidoDTO> findByStatus(TipoStatus status, Pageable pageable) throws RegraDeNegocioException {

        if (status == null) {
            Page<PedidoEntity> pedidosEntity = pedidoRepository.findAll(pageable);

            for (PedidoEntity pedido : pedidosEntity.getContent()) {
                pedido.setValorTotal(calculavalorTotal(pedido));
            }

            Page<PedidoDTO> entities =
                    pedidosEntity.
                            map(pedidoEntity -> fromEntity(pedidoEntity));
            return entities;
        }
        Page<PedidoEntity> pedidosEntity = pedidoRepository.findByStatus(status, pageable);

        for (PedidoEntity pedido : pedidosEntity.getContent()) {
            pedido.setValorTotal(calculavalorTotal(pedido));
        }

        Page<PedidoDTO> entities =
                pedidosEntity
                        .map(pedidoEntity -> fromEntity(pedidoEntity));
        return entities;

    }

}
