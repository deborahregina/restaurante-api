package com.dbc.trabalho_modulo_3.Restauranteapi.service;


import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoProdutoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ProdutoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.PedidoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;
    private final EmailService emailService;

    public PedidoDTO create (PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {

        for(PedidoProdutoDTO pedidoProduto : pedidoCreateDTO.getPedidoProduto()) {
            produtoService.getById(pedidoProduto.getIdproduto());
        }

        Integer idCliente = pedidoCreateDTO.getIdCliente();
        clienteService.findById(idCliente);



        List<PedidoProdutoEntity> listaPedidoProduto = pedidoCreateDTO.getPedidoProduto().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoEntity.class))
                .collect(Collectors.toList());

        PedidoEntity pedidoEntity = objectMapper.convertValue(pedidoCreateDTO, PedidoEntity.class);
        pedidoEntity.setProdutosDoPedido(listaPedidoProduto);

        Date dataPedido = new Date();
        String dateToStr = DateFormat.getInstance().format(dataPedido);
        pedidoEntity.setData(dateToStr);

        PedidoEntity pedidoCriado = pedidoRepository.save(pedidoEntity);
        List<PedidoProdutoDTO> listaPedidosProdutoDTO = pedidoCriado.getProdutosDoPedido().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                .collect(Collectors.toList());


        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoCriado, PedidoDTO.class);
        pedidoDTO.setPedidoProduto(listaPedidosProdutoDTO);
        pedidoDTO.setValorTotal(calculavalorTotal(pedidoDTO));


        //emailService.enviarEmailComTemplate(pedidoDTO);
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
        pedidoDTO.setValorTotal(calculavalorTotal(pedidoDTO));
        return pedidoDTO;
    }

    public void delete(Integer idPedido) throws RegraDeNegocioException {
        PedidoEntity pedidoEntity = pedidoRepository.findById(idPedido).orElseThrow(()-> new RegraDeNegocioException("Pedido não encontrado!"));
        pedidoRepository.delete(pedidoEntity);
    }



    public List<PedidoDTO> list() throws RegraDeNegocioException {

        List<PedidoEntity> listaPedidoEntities = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOList = new ArrayList<>();

        for(PedidoEntity pedidoEntity: listaPedidoEntities) {

            PedidoDTO pedidoConvertido = objectMapper.convertValue(pedidoEntity,PedidoDTO.class);

            List<PedidoProdutoDTO> pedidosProduto = pedidoEntity.getProdutosDoPedido().stream()
                    .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                    .collect(Collectors.toList());

            pedidoConvertido.setPedidoProduto(pedidosProduto);
            pedidoConvertido.setValorTotal(calculavalorTotal(pedidoConvertido));
            pedidoDTOList.add(pedidoConvertido);
        }

        return pedidoDTOList;
        
    }

    public PedidoDTO update (Integer idPedido, PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException {

        for(PedidoProdutoDTO pedidoProduto : pedidoCreateDTO.getPedidoProduto()) {
            produtoService.getById(pedidoProduto.getIdproduto());
        }

        Integer idCliente = pedidoCreateDTO.getIdCliente();
        clienteService.findById(idCliente);

        List<PedidoProdutoEntity> listaPedidoProduto = pedidoCreateDTO.getPedidoProduto().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoEntity.class))
                .collect(Collectors.toList());

        PedidoEntity pedidoEntity = objectMapper.convertValue(pedidoCreateDTO, PedidoEntity.class);
        pedidoEntity.setProdutosDoPedido(listaPedidoProduto);


        PedidoEntity pedidoCriado = pedidoRepository.update(idPedido, pedidoEntity);
        List<PedidoProdutoDTO> listaPedidosProdutoDTO = pedidoCriado.getProdutosDoPedido().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                .collect(Collectors.toList());


        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoCriado, PedidoDTO.class);
        pedidoDTO.setPedidoProduto(listaPedidosProdutoDTO);
        pedidoDTO.setValorTotal(calculavalorTotal(pedidoDTO));


        return pedidoDTO;
    }

    public BigDecimal calculavalorTotal(PedidoDTO pedidoDTO) throws RegraDeNegocioException {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(PedidoProdutoDTO pedidoProduto: pedidoDTO.getPedidoProduto()) {
            ProdutoDTO produto = produtoService.getById(pedidoProduto.getIdproduto());
            BigDecimal valorUnitario = produto.getValorUnitario();
            Integer quantidade = pedidoProduto.getQuantidade();
            valorTotal = valorTotal.add(valorUnitario.multiply(BigDecimal.valueOf(quantidade)));

        }
        return valorTotal;
    }




}
