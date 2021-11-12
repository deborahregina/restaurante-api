package com.dbc.trabalho_modulo_3.Restauranteapi.controller;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.PedidoService;
import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pedido")
@Validated
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping("/idCliente")
    @ApiOperation(value = "Cria novo pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido criado com sucesso"),
            @ApiResponse(code = 400, message = "Cliente não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public PedidoDTO create(@RequestParam("idCliente") Integer idCliente, @RequestBody @Valid PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException, MessagingException, TemplateException, IOException {
        return pedidoService.create(idCliente, pedidoCreateDTO);
    }


    @DeleteMapping("/{idPedido}")
    @ApiOperation(value = "Exclui os pedidos por id pedido")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido excluido com sucesso"),
            @ApiResponse(code = 400, message = "Pedido não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public void delete(@PathVariable("idPedido") Integer idPedido) throws RegraDeNegocioException {
        pedidoService.delete(idPedido);
    }

    @GetMapping("/idPedido")
    @ApiOperation(value = "Lista pedido por id ou todos os pedidos")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido(s) Listado(s) com sucesso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public List<PedidoDTO> list(@RequestParam(required = false) Integer idPedido) throws RegraDeNegocioException {
        return pedidoService.list(idPedido);
    }

    @PutMapping("/{idPedido}")
    @ApiOperation(value = "Altera os pedidos pelo id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Pedido alterado com sucesso"),
            @ApiResponse(code = 400, message = "Pedido não foi encontrado"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção no sistema")
    })
    public PedidoDTO update(@PathVariable("idPedido") Integer idPedido, @RequestBody @Valid PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException {
        return pedidoService.update(idPedido,pedidoCreateDTO);
    }


}
