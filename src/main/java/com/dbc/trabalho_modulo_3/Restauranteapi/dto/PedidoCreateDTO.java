package com.dbc.trabalho_modulo_3.Restauranteapi.dto;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.TipoStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PedidoCreateDTO {


    @ApiModelProperty(value = "Lista de Id de produtos e quantidade")
    private List<PedidoProdutoDTO> pedidoProduto;


    @NotNull(message = "0 ou 1")
    @ApiModelProperty(value = "0 - Aberto   1 - Arquivado")
    private TipoStatus status;

}
