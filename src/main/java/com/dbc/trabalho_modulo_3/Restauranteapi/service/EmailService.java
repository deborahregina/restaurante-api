package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.PedidoProdutoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ProdutoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;


    public void enviarEmailComTemplate(PedidoDTO pedidoDTO) throws MessagingException, IOException, TemplateException, RegraDeNegocioException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        String produtosDoPedido = "\n";
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        for(PedidoProdutoDTO pedidoProduto : pedidoDTO.getPedidoProduto()) {
            ProdutoDTO produto = produtoService.getById(pedidoProduto.getIdproduto());
            produtosDoPedido += "\n"+produto.getDescrição() + "       |       Valor Unitário: " + produto.getValorUnitario() + "\n";
        }

        helper.setFrom(remetente);
        helper.setTo(clienteService.getById(pedidoDTO.getIdCliente()).getEmail());
        helper.setSubject("Informações do novo pedido");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nomeUsuario", clienteService.getById(pedidoDTO.getIdCliente()).getNome());
        dados.put("produtos",produtosDoPedido);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }


}
