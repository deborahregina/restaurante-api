package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.dto.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;
    private final PedidoService pedidoService;
    private final ContatoRepository contatoRepository;
    private final PedidoRepository pedidoRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;



    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) throws Exception {

        ClienteEntity clienteCriado = objectMapper.convertValue(clienteCreateDTO, ClienteEntity.class);
        clienteRepository.save(clienteCriado);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteCriado, ClienteDTO.class);
        return clienteDTO;
    }

    public List<ClienteDTO> list(Integer idCliente) throws RegraDeNegocioException {

        List<ClienteDTO> clienteDTOS = new ArrayList<>();

        if (idCliente == null) {

            List<ClienteEntity> listaClienteEntity = clienteRepository.findAll();
            for (ClienteEntity cliente : listaClienteEntity) {
                clienteDTOS.add(fromEntity(cliente));
            }
            return clienteDTOS;
        }

        ClienteEntity cliente = findById(idCliente);


        clienteDTOS.add(fromEntity(cliente));
        return clienteDTOS;
    }


    private ClienteEntity findById(Integer id) throws RegraDeNegocioException {
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("cliente não encontrado"));
        return entity;
    }


    public ClienteDTO findByID(Integer idCliente) throws RegraDeNegocioException {
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);
        return clienteDTO;
    }

    public ClienteDTO update(Integer idCliente, ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteEntity clienteEntity = findById(idCliente);

        clienteEntity.setEmail(clienteCreateDTO.getEmail());
        clienteEntity.setCpf(clienteCreateDTO.getCpf());
        clienteEntity.setNome(clienteCreateDTO.getNome());

        ClienteEntity clienteAtualizado = clienteRepository.save(clienteEntity);


        return fromEntity(clienteAtualizado);
    }

    public void delete(Integer idCliente) throws Exception {

        ClienteEntity cliente = findById(idCliente);
        if (cliente.getPedidos() != null) {
            for (PedidoEntity pedido : cliente.getPedidos()) {
                if (pedido.getProdutosDoPedido() != null) {
                    for (PedidoProdutoEntity pedidoProduto : pedido.getProdutosDoPedido()) {
                        pedidoProdutoRepository.delete(pedidoProduto);
                    }
                }
                pedidoRepository.delete(pedido);
            }
            clienteRepository.delete(cliente);
        }
    }

    public Page<ClienteDTO> findAll(Pageable pageable) {

        Page<ClienteDTO> entities =
                clienteRepository.findAll(pageable)
                        .map(clienteEntity -> fromEntity(clienteEntity));
        return entities;
    }

    private ClienteDTO fromEntity(ClienteEntity cliente) {

        ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);

        clienteDTO.setIdCliente(cliente.getIdCliente());
        Set<EnderecoEntity> enderecosEntity = cliente.getEnderecos();
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        for (EnderecoEntity endeco : enderecosEntity) {
            EnderecoDTO enderecoDTO = objectMapper.convertValue(endeco, EnderecoDTO.class);
            enderecoDTO.setIdCliente(cliente.getIdCliente());
            enderecosDTO.add(enderecoDTO);

        }
        Set<ContatoEntity> contatoEntities = cliente.getContatos();
        List<ContatoDTO> contatosDTO = new ArrayList<>();
        for (ContatoEntity contato : contatoEntities) {
            ContatoDTO contatoDTO = objectMapper.convertValue(contato, ContatoDTO.class);
            contatoDTO.setIdCliente(cliente.getIdCliente());
            contatosDTO.add(contatoDTO);
        }

        clienteDTO.setContatos(contatosDTO);
        clienteDTO.setEnderecos(enderecosDTO);
        return clienteDTO;
    }
}


