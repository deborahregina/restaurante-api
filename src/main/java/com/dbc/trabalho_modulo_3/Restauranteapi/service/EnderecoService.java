package com.dbc.trabalho_modulo_3.Restauranteapi.service;


import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.EnderecoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.EnderecoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.EnderecoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;

    public List<EnderecoDTO> list() {
        return enderecoRepository.list().stream()
                .map(endereco -> objectMapper.convertValue(endereco, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO create(Integer idCliente, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        clienteRepository.getById(idCliente);
        EnderecoEntity enderecoEntity1 = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        enderecoEntity1.setIdCliente(idCliente);
        EnderecoEntity enderecoCriado = enderecoRepository.create(enderecoEntity1);
        EnderecoDTO dto = objectMapper.convertValue(enderecoCriado, EnderecoDTO.class);
        enderecoEntity1.setIdCliente(idCliente);
        return dto;
    }

    public EnderecoDTO update(Integer idEndereco, @Valid EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        EnderecoEntity enderecoEntity1 = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity enderecoCriado = enderecoRepository.update(idEndereco, enderecoEntity1);
        EnderecoDTO dto = objectMapper.convertValue(enderecoCriado, EnderecoDTO.class);
        return dto;
    }

    public void delete(Integer idEndereco) throws Exception {
        enderecoRepository.delete(idEndereco);
    }

}
