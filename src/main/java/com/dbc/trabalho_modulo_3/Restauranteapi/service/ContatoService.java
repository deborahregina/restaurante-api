package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.dto.ClienteDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.ContatoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.ContatoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ContatoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ContatoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContatoService {
    private final ContatoRepository contatoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;


    public ContatoDTO create(Integer idCliente, ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {
        ClienteEntity clienteEntity = clienteRepository.findById(idCliente).orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado!"));
        ContatoEntity contatoEntity = objectMapper.convertValue(contatoCreateDTO, ContatoEntity.class);
        contatoEntity.setClienteEntity(clienteEntity);
        ContatoEntity contatoCriado = contatoRepository.save(contatoEntity);
        ContatoDTO dto = objectMapper.convertValue(contatoCriado, ContatoDTO.class);
        ClienteDTO clienteDTO = objectMapper.convertValue(clienteEntity, ClienteDTO.class);
        dto.setIdCliente(clienteDTO.getIdCliente());
        return dto;
    }

    public List<ContatoDTO> list(Integer idContato) throws RegraDeNegocioException {

        List<ContatoDTO> listaContatosDTO = new ArrayList<>();

        if(idContato == null) {
            List<ContatoEntity> listaContatosEntity = contatoRepository.findAll();
            for(ContatoEntity contatoEntity : listaContatosEntity) {

                ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
                contatoDTO.setIdCliente(contatoEntity.getClienteEntity().getIdCliente());

                listaContatosDTO.add(contatoDTO);
            }
            return listaContatosDTO;
        }

        ContatoEntity contatoEntity = findById(idContato);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity,ContatoDTO.class);
        contatoDTO.setIdCliente(contatoEntity.getClienteEntity().getIdCliente());
        listaContatosDTO.add(contatoDTO);

        return listaContatosDTO;

    }

    private ContatoEntity findById(Integer id) throws RegraDeNegocioException {
        ContatoEntity contatoentity = contatoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Contato não encontrado"));
        return contatoentity;
    }

    public ContatoDTO getById(Integer id) throws RegraDeNegocioException {
        ContatoEntity entity = findById(id);
        ContatoDTO dto = objectMapper.convertValue(entity, ContatoDTO.class);
        dto.setIdCliente(entity.getClienteEntity().getIdCliente());
        return dto;
    }

    public ContatoDTO update(Integer idContato,
                             ContatoCreateDTO contatoCreateDTO) throws RegraDeNegocioException {

        ContatoEntity contatoEntity = findById(idContato);
        contatoEntity.setTipoContato(contatoCreateDTO.getTipoContato());
        contatoEntity.setDescricao(contatoCreateDTO.getDescricao());
        contatoEntity.setNumero(contatoCreateDTO.getNumero());
        ContatoEntity contatoupdate = contatoRepository.save(contatoEntity);
        ContatoDTO contatoDTO = objectMapper.convertValue(contatoupdate, ContatoDTO.class);
        contatoDTO.setIdCliente(contatoupdate.getClienteEntity().getIdCliente());
        return contatoDTO;

    }

    public void delete(Integer id) throws RegraDeNegocioException {
        ContatoEntity contato = findById(id);
        contatoRepository.delete(contato);
    }

    public Set<ContatoDTO> getByIdCliente(Integer idCliente) throws RegraDeNegocioException {
        ClienteEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));

        Set<ContatoDTO> listaDTO = cliente.getContatos().stream().map(contatoEntity -> {
            ContatoDTO contatoDTO = objectMapper.convertValue(contatoEntity, ContatoDTO.class);
            contatoDTO.setIdCliente(idCliente);
            return contatoDTO;
        }).collect(Collectors.toSet());

        return listaDTO;
    }
}

