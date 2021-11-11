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

    public ClienteDTO create(ClienteCreateDTO clienteCreateDTO) throws Exception {

        ClienteEntity clienteCriado = objectMapper.convertValue(clienteCreateDTO, ClienteEntity.class);
        clienteRepository.save(clienteCriado);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteCriado, ClienteDTO.class);
        return clienteDTO;
    }



}
