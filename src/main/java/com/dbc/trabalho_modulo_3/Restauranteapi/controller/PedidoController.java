package com.dbc.trabalho_modulo_3.Restauranteapi.controller;

import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/pedido")
@Validated
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public PedidoDTO create(@RequestBody @Valid PedidoCreateDTO pedidoCreateDTO) throws RegraDeNegocioException {
        return pedidoService.create(pedidoCreateDTO);
    }

    @GetMapping("/{idPedido}")
    public PedidoDTO getByID(@PathVariable("idPedido") Integer idPedido) throws RegraDeNegocioException {
        return pedidoService.getByID(idPedido);
    }

    @DeleteMapping("/{idPedido}")
    public void delete(@PathVariable("idPedido") Integer idPedido) throws RegraDeNegocioException {
        pedidoService.delete(idPedido);
    }

    @GetMapping("/teste")
    public List<PedidoDTO> list() throws RegraDeNegocioException {
        return pedidoService.list();
    }

    @PutMapping("/{idPedido}")
    public PedidoDTO update(@PathVariable("idPedido") Integer idPedido,@RequestBody @Valid PedidoCreateDTO pedidoCreateDTO ) throws RegraDeNegocioException {
        return pedidoService.update(idPedido,pedidoCreateDTO);
    }

    @GetMapping
    public  String retornaString() {
        return System.getenv("EMAIL") + System.getenv("SENHA");
    }
}
