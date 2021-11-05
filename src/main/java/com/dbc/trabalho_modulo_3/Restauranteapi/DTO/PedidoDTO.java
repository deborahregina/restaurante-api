package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import lombok.Data;

@Data
public class PedidoDTO extends PedidoCreateDTO {

    private Integer idPedido;

    private String status;

    private Double valorTotal;


}
