package com.dbc.trabalho_modulo_3.Restauranteapi.schedule;

import com.dbc.trabalho_modulo_3.Restauranteapi.dto.CaixaDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.EmailDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.kafka.Producer;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.PedidoRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleEnvioSomaPedidos {
    private final PedidoRepository pedidoRepository;
    private final Producer producer;

//    @Scheduled(fixedDelay = 120000)
    @Scheduled(cron = "0 0 * * * *")
    public void enviaKafkaCaixa() throws JsonProcessingException {
        BigDecimal somaDosPedidos = pedidoRepository.somaDosPedidos();
        LocalDateTime dataPedido = LocalDateTime.now();

        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        CaixaDTO caixaDTO = new CaixaDTO();
        caixaDTO.setValorCaixa(somaDosPedidos);
        caixaDTO.setDataCaixa(dataPedido.format(formatData));
        producer.sendMessageSomaPedidos(caixaDTO);


    }
}