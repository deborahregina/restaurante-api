package com.dbc.trabalho_modulo_3.Restauranteapi.service;


import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ContatoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.EnderecoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.EnderecoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ContatoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.EnderecoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteService clienteService;

    public EnderecoDTO create(Integer idCliente, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        ClienteDTO clienteDTO = clienteService.findByID(idCliente);
        EnderecoEntity enderecoEntity = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        enderecoEntity.setClienteEntity(objectMapper.convertValue(clienteDTO, ClienteEntity.class));
        EnderecoEntity atualizado = enderecoRepository.save(enderecoEntity);
        EnderecoDTO enderecoDTO = objectMapper.convertValue(atualizado, EnderecoDTO.class);
        enderecoDTO.setIdCliente(idCliente);
        return enderecoDTO;
    }

    public List<EnderecoDTO> list(Integer idEndereco) throws RegraDeNegocioException {
        List<EnderecoDTO> listaEnderecoDTO = new ArrayList<>();

        if(idEndereco == null) {
            List<EnderecoEntity> listaEnderecoEntity = enderecoRepository.findAll();
            for(EnderecoEntity enderecoEntity : listaEnderecoEntity) {

                EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
                enderecoDTO.setIdCliente(enderecoEntity.getClienteEntity().getIdCliente());

                listaEnderecoDTO.add(enderecoDTO);
            }

            return listaEnderecoDTO;
        }

        EnderecoEntity enderecoEntity = findById(idEndereco);

        EnderecoDTO enderecoDTO = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
        enderecoDTO.setIdCliente(enderecoEntity.getClienteEntity().getIdCliente());
        listaEnderecoDTO.add(enderecoDTO);

        return listaEnderecoDTO;

    }

    public EnderecoDTO getById(Integer id) throws Exception{
        EnderecoEntity enderecoEntity = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));
        EnderecoDTO dto = objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
        dto.setIdCliente(enderecoEntity.getClienteEntity().getIdCliente());
        return dto;
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws Exception{
        EnderecoEntity enderecoEntity = findById(idEndereco);
        EnderecoEntity endereco = objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
        endereco.setIdEndereco(idEndereco);
        endereco.setClienteEntity(enderecoEntity.getClienteEntity());
        EnderecoEntity update = enderecoRepository.save(endereco);
        EnderecoDTO dto = objectMapper.convertValue(update, EnderecoDTO.class);
        dto.setIdCliente(update.getClienteEntity().getIdCliente());
        return dto;
    }

    private EnderecoEntity findById(Integer id) throws RegraDeNegocioException{
        EnderecoEntity enderecoEntity = enderecoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));

        return enderecoEntity;
    }

    public void delete(Integer id) throws Exception {
        EnderecoEntity entity = findById(id);
        enderecoRepository.delete(entity);
    }
}
