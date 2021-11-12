package com.dbc.trabalho_modulo_3.Restauranteapi.controller;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.ClienteService;
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
@RequestMapping("/cliente")
@Validated
@RequiredArgsConstructor
@Slf4j
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/idCliente")
    @ApiOperation(value = "Lista cliente por id ou todos os clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente(s) listado(s) com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<ClienteDTO> list(@RequestParam(required = false) Integer idCliente) throws RegraDeNegocioException {
        return clienteService.list(idCliente);
    }

    @GetMapping("/{idCliente}")
    @ApiOperation(value = "Lista os clientes pelo id do cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente listado com sucesso"),
            @ApiResponse(code = 400, message = "Cliente não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ClienteDTO getById(@PathVariable("idCliente") Integer idCliente) throws RegraDeNegocioException {
        return clienteService.getByID(idCliente);
    }

    @PostMapping
    @ApiOperation(value = "Cria novo cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente criado com sucesso"),
            @ApiResponse(code = 400, message = "Dados do cliente inconsistentes"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ClienteDTO create(@RequestBody @Valid ClienteCreateDTO clienteCreateDTO) throws Exception {
        return clienteService.create(clienteCreateDTO);
    }

    @PutMapping("/{idCliente}")
    @ApiOperation(value = "Altera o cliente pelo id Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente criado com sucesso"),
            @ApiResponse(code = 400, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public ClienteDTO update(@PathVariable("idCliente") Integer id,
                            @RequestBody @Valid ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        return clienteService.update(id, clienteCreateDTO);
    }

    @DeleteMapping("/{idCliente}")
    @ApiOperation(value = "Exclui o cliente pelo id Cliente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cliente excluido com sucesso"),
            @ApiResponse(code = 400, message = "Cliente não encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public void delete(@PathVariable("idCliente") Integer id) throws RegraDeNegocioException {
        clienteService.delete(id);
    }

}
