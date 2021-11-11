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
    private final ObjectMapper objectMapper;

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        EnderecoEntity EnderecoEntity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity atualizado = enderecoRepository.save(EnderecoEntity);
        EnderecoDTO dto = objectMapper.convertValue(atualizado, EnderecoDTO.class);
        return dto;
    }

    public List<EnderecoDTO> list(){
        return enderecoRepository.findAll().stream()
                .map(x -> objectMapper.convertValue(x, EnderecoDTO.class))
                .collect(Collectors.toList());
    }

    public EnderecoDTO listByEndereco(Integer id) throws Exception{
        EnderecoEntity EnderecoEntity = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        EnderecoDTO dto = objectMapper.convertValue(EnderecoEntity, EnderecoDTO.class);
        return dto;
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws Exception{
        findById(idEndereco);
        EnderecoEntity EnderecoEntity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        EnderecoEntity.setIdEndereco(idEndereco);
        EnderecoEntity update = enderecoRepository.save(EnderecoEntity);
        EnderecoDTO dto = objectMapper.convertValue(update, EnderecoDTO.class);
        return dto;
    }

    private EnderecoDTO findById(Integer id) throws RegraDeNegocioException{
        EnderecoEntity EnderecoEntity = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        EnderecoDTO dto = objectMapper.convertValue(EnderecoEntity, EnderecoDTO.class);
        return dto;
    }

    public void delete(Integer id) throws Exception {
        EnderecoDTO dto = findById(id);
        EnderecoEntity  EnderecoEntity= objectMapper.convertValue(dto, EnderecoEntity.class);
        enderecoRepository.delete(EnderecoEntity);
    }
}
