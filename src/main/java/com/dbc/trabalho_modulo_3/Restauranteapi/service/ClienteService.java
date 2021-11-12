package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ContatoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.EnderecoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {


    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;

    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) throws Exception {

        ClienteEntity clienteCriado = objectMapper.convertValue(clienteCreateDTO, ClienteEntity.class);
        clienteRepository.save(clienteCriado);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteCriado, ClienteDTO.class);
        return clienteDTO;
    }

    public List<ClienteDTO> list(Integer idCliente) throws RegraDeNegocioException {

        List<ClienteEntity> listaClienteEntity = clienteRepository.findAll();

        List<ClienteDTO> clienteDTOS = new ArrayList<>();

        if (idCliente == null) {
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
        return clienteDTOS;
    }


    public ClienteEntity findById(Integer id) throws RegraDeNegocioException {
        ClienteEntity entity = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("cliente não encontrado"));
        return entity;
    }

    public ClienteDTO update(Integer idCliente, ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteEntity clienteEntity = findById(idCliente);

        ClienteEntity clienteConvertido = objectMapper.convertValue(clienteCreateDTO, ClienteEntity.class);

        clienteConvertido.setContatos(clienteEntity.getContatos());
        clienteConvertido.setEnderecos(clienteEntity.getEnderecos());
        ClienteEntity clienteAtualizado = clienteRepository.save(clienteConvertido);

        ClienteDTO clienteAtualizadoDTO = objectMapper.convertValue(clienteAtualizado, ClienteDTO.class);

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

    public void delete(Integer idCliente) throws RegraDeNegocioException {
        ClienteEntity cliente = findById(idCliente);
        clienteRepository.delete(cliente);
    }

}
