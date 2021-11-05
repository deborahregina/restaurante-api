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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public PedidoDTO create (PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException {

        for(PedidoProdutoDTO pedidoProduto : pedidoCreateDTO.getPedidoProduto()) {
            produtoService.getById(pedidoProduto.getIdproduto());
        }

        Integer idCliente = pedidoCreateDTO.getIdCliente();
        clienteService.getById(idCliente);



        List<PedidoProdutoEntity> listaPedidoProduto = pedidoCreateDTO.getPedidoProduto().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoEntity.class))
                .collect(Collectors.toList());

        PedidoEntity pedidoEntity = objectMapper.convertValue(pedidoCreateDTO, PedidoEntity.class);
        pedidoEntity.setProdutosDoPedido(listaPedidoProduto);


        PedidoEntity pedidoCriado = pedidoRepository.create(pedidoEntity);
        List<PedidoProdutoDTO> listaPedidosProdutoDTO = pedidoCriado.getProdutosDoPedido().stream()
                .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                .collect(Collectors.toList());


        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoCriado, PedidoDTO.class);
        pedidoDTO.setPedidoProduto(listaPedidosProdutoDTO);
        pedidoDTO.setValorTotal(calculavalorTotal(pedidoDTO));

        return pedidoDTO;
    }

    public PedidoDTO getByID(Integer idPedido) throws RegraDeNegocioException {

        PedidoEntity pedidoEntity = pedidoRepository.getByID(idPedido);
        PedidoDTO pedidoDTO = objectMapper.convertValue(pedidoEntity, PedidoDTO.class);

        List<PedidoProdutoDTO> listaPedidoProduto;

        listaPedidoProduto = pedidoEntity.getProdutosDoPedido().stream()
                    .map(pedidoProduto -> objectMapper.convertValue(pedidoProduto, PedidoProdutoDTO.class))
                    .collect(Collectors.toList());

        pedidoDTO.setPedidoProduto(listaPedidoProduto);
        pedidoDTO.setValorTotal(calculavalorTotal(pedidoDTO));
        return pedidoDTO;
    }

    public void delete(Integer idPedido) throws RegraDeNegocioException {
        pedidoRepository.delete(idPedido);
    }



    public List<PedidoDTO> list() throws RegraDeNegocioException {

        List<PedidoEntity> listaPedidoEntities = pedidoRepository.list();
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
        clienteService.getById(idCliente);

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

    public Double calculavalorTotal(PedidoDTO pedidoDTO) throws RegraDeNegocioException {
        Double valorTotal = 0.0;
        for(PedidoProdutoDTO pedidoProduto: pedidoDTO.getPedidoProduto()) {
            ProdutoDTO produto = produtoService.getById(pedidoProduto.getIdproduto());
            Double valorUnitario = produto.getValorUnitario();
            Integer quantidade = pedidoProduto.getQuantidade();
            valorTotal += valorUnitario*quantidade;;
        }
        return valorTotal;
    }

}
