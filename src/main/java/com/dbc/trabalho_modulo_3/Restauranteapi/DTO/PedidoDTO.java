package com.dbc.trabalho_modulo_3.Restauranteapi.DTO;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PedidoDTO extends PedidoCreateDTO {

    private Integer idPedido;

    @NotNull
    private String status;

    private Double valorTotal;


}
