package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ContatoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ContatoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ContatoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;


    public ContatoDTO create(Integer id, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        ContatoEntity contoEntity = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        PessoaEntity pessoaEntity = pessoaRepository.findById(id).stream()
                .filter(x -> x.getIdPessoa().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        contoEntity.setPessoaEntity(pessoaEntity);
        ContatoEntity atualizado = contatoRepository.save(contoEntity);
        ContatoDTO dto = objectMapper.convertValue(atualizado, ContatoDTO.class);
        return null;
    }

    public List<ContatoDTO> list(){

        return contatoRepository.findAll().stream()
                .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                .collect(Collectors.toList());

    }
    private ContatoEntity findById(Integer id) throws RegraDeNegocioException {
        ContatoEntity contatoentity = contatoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        return contatoentity;
    }

    public ContatoDTO getById(Integer id) throws RegraDeNegocioException {
        ContatoEntity entity = findById(id);
        ContatoDTO dto = objectMapper.convertValue(entity, ContatoDTO.class);
        return dto;
    }

    public ContatoDTO update(Integer idContato,
                             ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {

        findById(idContato);
        ContatoEntity contato = objectMapper.convertValue(contatoCreateDTO,ContatoEntity.class);
        contato.setIdContato(idContato);
        ContatoEntity contatoupdate = contatoRepository.save(contato);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoupdate, ContatoDTO.class);

        return contatoDTO;

    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ContatoEntity contato = findById(id);
        contatoRepository.delete(contato);
    }
}

