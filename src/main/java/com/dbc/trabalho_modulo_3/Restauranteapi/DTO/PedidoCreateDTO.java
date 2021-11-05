package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PedidoCreateDTO {

    private Integer idCliente;
    private List<PedidoProdutoDTO> pedidoProduto;
    private String status;

}
