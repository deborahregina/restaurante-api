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
        private final ClienteRepository clienteRepository;
        private final ObjectMapper objectMapper;
        private final ClienteService clienteService;




        public List<ContatoDTO> list(){
            return contatoRepository.list().stream()
                    .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                    .collect(Collectors.toList());


        }

        public List<ContatoDTO> listPorIdCliente(Integer idCliente) throws RegraDeNegocioException {
            clienteRepository.getById(idCliente);
            return contatoRepository.listByIdCliente(idCliente).stream()
                    .map(contato -> objectMapper.convertValue(contato, ContatoDTO.class))
                    .collect(Collectors.toList());
        }

        public ContatoDTO create(Integer idCliente, ContatoCreateDTO contatoCreateDTO) throws Exception {

            clienteService.getById(idCliente);

            ContatoEntity contatoEntity1 = objectMapper.convertValue(contatoCreateDTO,ContatoEntity.class);
            contatoEntity1.setIdCliente(idCliente);
            ContatoEntity contatoCriado = contatoRepository.create(idCliente, contatoEntity1);
            ContatoDTO contatoDTO = objectMapper.convertValue(contatoCriado,ContatoDTO.class);
            return contatoDTO;

        }

        public ContatoDTO update(Integer idContato, ContatoCreateDTO contatoCreateDTO) throws Exception {


            ContatoEntity contatoEntity = objectMapper.convertValue(contatoCreateDTO,ContatoEntity.class);
            ContatoEntity contatoEntity1 = contatoRepository.update(idContato, contatoEntity);
            ContatoDTO dto = objectMapper.convertValue(contatoEntity1,ContatoDTO.class);
            return dto;
        }

        public void delete(Integer idContato) throws Exception {
            contatoRepository.delete(idContato);
        }
    }

