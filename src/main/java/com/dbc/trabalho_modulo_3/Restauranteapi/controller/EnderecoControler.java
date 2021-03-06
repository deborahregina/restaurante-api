package com.dbc.trabalho_modulo_3.Restauranteapi.controller;


import com.dbc.trabalho_modulo_3.Restauranteapi.dto.EnderecoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.EnderecoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/endereco")
@Validated
@Slf4j
@RequiredArgsConstructor

public class EnderecoControler {

    private final EnderecoService enderecoService;

    @GetMapping("/idEndereco")
    @ApiOperation( value = "Lista enderecos por id ou todos os enderecos")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Endereço(s)  listado(s) com sucesso"),
            @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<EnderecoDTO> list(@RequestParam(required = false) Integer idEndereco) throws RegraDeNegocioException {
        return enderecoService.list(idEndereco);
    }

    @ApiOperation( value = "Criar um novo endereço por id de pessoa")
    @ApiResponses( value = {
            @ApiResponse(code =200, message = "Endereço  criado com sucesso"),
            @ApiResponse( code = 400, message = "endereço não encontrado"),
            @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
    })
    @PostMapping("/{idPessoa}")
    public EnderecoDTO create(@PathVariable("idPessoa") Integer idCliente,
                              @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
        return enderecoService.create(idCliente, enderecoCreateDTO);
    }


        @ApiOperation( value = "Atualizar endereço por id")
        @ApiResponses( value = {
                @ApiResponse(code =200, message = "Endereço  atualizado com sucesso"),
                @ApiResponse( code = 400, message = "endereço não encontrado"),
                @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
        })
        @PutMapping("/{idEndereco}")
        public EnderecoDTO update(@PathVariable("idEndereco") Integer idEndereco,
                                  @Valid @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws Exception {
            return enderecoService.update(idEndereco, enderecoCreateDTO);
        }


        @ApiOperation( value = "Deletar um  endereço por id")
        @ApiResponses( value = {
                @ApiResponse(code =200, message = "Endereço  deletado com sucesso"),
                @ApiResponse( code = 400, message = "endereço não encontrado"),
                @ApiResponse( code = 500, message = "Foi gerada uma exceção no sistema")
        })
        @DeleteMapping("/{idEndereco}")
        public void delete(@PathVariable("idEndereco") Integer idEndereco) throws Exception {
            log.info("deletando endereco");
            enderecoService.delete(idEndereco);
            log.info("endereco deletada com sucesso");
        }

}
