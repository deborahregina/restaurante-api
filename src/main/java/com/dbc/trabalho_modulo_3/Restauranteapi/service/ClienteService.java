package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;

    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {

        ClienteEntity clienteEntity = objectMapper.convertValue(clienteCreateDTO, ClienteEntity.class);
        ClienteEntity clienteCriado = clienteRepository.create(clienteEntity);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteCriado, ClienteDTO.class);
        //emailService.enviarEmailSimples(clienteCriado);
        return clienteDTO;
    }

    public List<ClienteDTO> list() {
        return clienteRepository.list().stream()
                .map(cliente -> objectMapper.convertValue(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public ClienteDTO getById(Integer idCliente) throws RegraDeNegocioException {
        ClienteDTO clienteByID = objectMapper.convertValue(clienteRepository.getById(idCliente),ClienteDTO.class);
        return clienteByID;
    }

    public ClienteDTO update(Integer id,
                            ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        ClienteEntity clienteEntity = objectMapper.convertValue(clienteCreateDTO, ClienteEntity.class);
        ClienteEntity cliente = clienteRepository.update(id, clienteEntity);
        ClienteDTO dto = objectMapper.convertValue(cliente, ClienteDTO.class);
        return dto;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ClienteEntity clienteRecuperado = clienteRepository.getById(id);
        clienteRepository.delete(clienteRecuperado.getIdCliente());
    }



}
