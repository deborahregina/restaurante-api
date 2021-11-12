package com.dbc.trabalho_modulo_3.Restauranteapi.controller;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ContatoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ContatoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ContatoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.ContatoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping("/contato")
@Validated
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;

    @PostMapping("/{idCliente}")
    @ApiOperation( value = "Criar contato ")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Contato criado  sucesso"),
            @ApiResponse( code = 400, message = "contato não encontrado"),
            @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ContatoDTO create(@PathVariable("idCliente") Integer idCliente, @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception {
        log.info("Contato criado");
        ContatoDTO contatonew = contatoService.create(idCliente, contatoCreateDTO);
        log.info("Contatao criado com sucesso");
        return contatonew;
    }

    @GetMapping("/idContato")
    @ApiOperation( value = "Lista contatos por id ou todos os contatos")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Contato(s) listado(s) com sucesso"),
            @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<ContatoDTO> list(@RequestParam(required = false) Integer idContato) throws RegraDeNegocioException {
        return contatoService.list(idContato);
    }


    @PutMapping("/{idContato}")
    @ApiOperation( value = "Atualizar contato por id do contato")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Contato atualizado com sucesso"),
            @ApiResponse( code = 400, message = "contato não encontrado"),
            @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ContatoDTO update(@PathVariable("idContato") Integer idContato,
                             @RequestBody ContatoCreateDTO contatoCreateDTO) throws Exception {
        return contatoService.update(idContato, contatoCreateDTO);
    }

    @DeleteMapping("/{idContato}")
    @ApiOperation( value = "Deletar contato por id do contato")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Contato deletado com sucesso"),
            @ApiResponse( code = 400, message = "contato não encontrado"),
            @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public void delete(@PathVariable("idContato") Integer idContato) throws Exception {
        contatoService.delete(idContato);
    }
}


