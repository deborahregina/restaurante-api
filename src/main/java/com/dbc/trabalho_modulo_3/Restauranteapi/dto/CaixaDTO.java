package com.dbc.trabalho_modulo_3.Restauranteapi.dto;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CaixaDTO {
    private BigDecimal valorCaixa;
    private String dataCaixa;
}
