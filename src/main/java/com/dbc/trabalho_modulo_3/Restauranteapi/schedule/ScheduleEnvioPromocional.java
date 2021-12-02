package com.dbc.trabalho_modulo_3.Restauranteapi.schedule;

import com.dbc.trabalho_modulo_3.Restauranteapi.dto.EmailDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.kafka.Producer;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ClienteRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.PedidoRepository;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScheduleEnvioPromocional {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    NumberFormat formatter = new DecimalFormat("#0.00");
    private final Producer producer;

    //@Scheduled(cron = "0 0 18 * * FRI")
    @Scheduled(fixedDelay = 60000)
    public void enviaKafkaPromocao() throws JsonProcessingException {
        List<ClienteEntity> listaClientes = clienteRepository.findAll();
        List<ProdutoEntity> listaPromocoes = produtoRepository.buscaPromocao();

        String mensagemCompleta = "Promoções para você aproveitar o final de semana: \n";
        String promocoes = "";
        for(ProdutoEntity produto: listaPromocoes) {
            promocoes += "<br>" + produto.getDescricao() + "<br> Preço R$ " + produto.getValorUnitario() +"<br>";
        }

        mensagemCompleta += promocoes;
        for(ClienteEntity cliente: listaClientes) {
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setNomeCliente(cliente.getNome());
            emailDTO.setDestinatario(cliente.getEmail());
            emailDTO.setMensagem(mensagemCompleta);
            emailDTO.setAssunto("Promoções do final de semana");
            producer.sendMessagePromocoes(emailDTO);

        }

    }





}
