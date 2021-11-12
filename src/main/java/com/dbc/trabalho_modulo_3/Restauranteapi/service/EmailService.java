package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.*;
import com.dbc.trabalho_modulo_3.Restauranteapi.DTO.ClienteDTO;
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
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String remetente;
    private final Configuration configuration;
    private final ProdutoService produtoService;

    NumberFormat formatter = new DecimalFormat("#0.00");

    public void enviarEmailComTemplate(PedidoDTO pedidoDTO, ClienteDTO cliente) throws MessagingException, IOException, TemplateException, RegraDeNegocioException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        String produtosDoPedido = "";
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        for(PedidoProdutoDTO pedidoProduto : pedidoDTO.getPedidoProduto()) {
            ProdutoDTO produto = produtoService.getById(pedidoProduto.getIdproduto());
            produtosDoPedido += "<br>"+pedidoProduto.getQuantidade()+ "x "+ produto.getDescricao() + ":                                            " +
                    "    R$ " + formatter.format(produto.getValorUnitario().multiply(BigDecimal.valueOf(pedidoProduto.getQuantidade())));
        }

        helper.setFrom(remetente);
        helper.setTo(cliente.getEmail());
        helper.setSubject("Informações do novo pedido");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nomeUsuario", cliente.getNome());
        dados.put("produtos",produtosDoPedido);
        dados.put("valorTotal", "R$ " + formatter.format(pedidoDTO.getValorTotal()));
        dados.put("data", pedidoDTO.getData());
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }


}
