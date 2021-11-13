package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

                ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);
                clienteDTO.setContatos(cliente.getContatos().stream()
                        .map(contatoEntity -> {

                            ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
                            contatoDTO.setIdCliente(clienteDTO.getIdCliente());
                            return contatoDTO;

                        }).collect(Collectors.toList()));


                clienteDTO.setEnderecos(cliente.getEnderecos().stream()
                        .map(enderecoEntity -> {

                            EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
                            enderecoDTO.setIdCliente(clienteDTO.getIdCliente());
                            return enderecoDTO;
                        }).collect(Collectors.toList()));

                clienteDTOS.add(clienteDTO);
            }
            return clienteDTOS;
        }

        ClienteEntity cliente = findById(idCliente);

        ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);
        clienteDTO.setContatos(cliente.getContatos().stream()
                .map(contatoEntity -> {

                    ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
                    contatoDTO.setIdCliente(cliente.getIdCliente());
                    return contatoDTO;

                }).collect(Collectors.toList()));


        clienteDTO.setEnderecos(cliente.getEnderecos().stream()
                .map(enderecoEntity -> {

                    EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
                    enderecoDTO.setIdCliente(cliente.getIdCliente());
                    return enderecoDTO;
                }).collect(Collectors.toList()));

        clienteDTOS.add(clienteDTO);
        return clienteDTOS;
    }


    private ClienteEntity findById(Integer id) throws RegraDeNegocioException {
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("cliente não encontrado"));
        return entity;
    }

    public ClienteDTO getByID(Integer id) throws RegraDeNegocioException {
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("cliente não encontrado"));
        ClienteDTO clienteDTO = objectMapper.convertValue(entity, ClienteDTO.class);
        List<EnderecoDTO> listaEnd = entity.getEnderecos().stream()
                .map(enderecoEntity -> {
                    EnderecoDTO endDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
                    endDTO.setIdCliente(enderecoEntity.getClienteEntity().getIdCliente());
                    return endDTO;
                }).collect(Collectors.toList());

        List<ContatoDTO> listaCont = entity.getContatos().stream()
                .map(contatoEntity -> {
                    ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
                    contatoDTO.setIdCliente(contatoEntity.getClienteEntity().getIdCliente());
                    return contatoDTO;
                }).collect(Collectors.toList());


        clienteDTO.setEnderecos(listaEnd);
        clienteDTO.setContatos(listaCont);
        return clienteDTO;
    }

    public ClienteDTO findByID(Integer idCliente) throws RegraDeNegocioException {
        ClienteEntity cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        ClienteDTO clienteDTO = objectMapper.convertValue(cliente, ClienteDTO.class);
        return clienteDTO;
    }

    public ClienteDTO update(Integer idCliente, ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteEntity clienteEntity = findById(idCliente);

        ClienteEntity clienteConvertido = objectMapper.convertValue(clienteCreateDTO, ClienteEntity.class);


        ClienteEntity clienteAtualizado = clienteRepository.save(clienteConvertido);

        ClienteDTO clienteAtualizadoDTO = objectMapper.convertValue(clienteAtualizado, ClienteDTO.class);

        clienteConvertido.setContatos(clienteEntity.getContatos());
        clienteConvertido.setEnderecos(clienteEntity.getEnderecos());

        clienteAtualizadoDTO.setEnderecos(clienteAtualizado.getEnderecos().stream().map(
                enderecoEntity -> {
                    EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
                    enderecoDTO.setIdCliente(clienteAtualizadoDTO.getIdCliente());
                    return enderecoDTO;
                }
        ).collect(Collectors.toList()));

        clienteAtualizadoDTO.setContatos(clienteAtualizado.getContatos().stream().map(
                contatoEntity -> {
                    ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
                    contatoDTO.setIdCliente(clienteAtualizadoDTO.getIdCliente());
                    return contatoDTO;
                }
        ).collect(Collectors.toList()));

        return clienteAtualizadoDTO;
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


}


