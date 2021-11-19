package com.dbc.trabalho_modulo_3.Restauranteapi.service;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ClienteEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.PedidoProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.ProdutoEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import com.dbc.trabalho_modulo_3.Restauranteapi.repository.ProdutoRepository;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
import java.time.format.DateTimeFormatter;
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
    private final ProdutoRepository produtoRepository;

    NumberFormat formatter = new DecimalFormat("#0.00");

    public void enviarEmailComTemplate(PedidoEntity pedido, ClienteEntity cliente) throws MessagingException, IOException, TemplateException, RegraDeNegocioException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        String produtosDoPedido = "";
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        for(PedidoProdutoEntity pedidoProduto : pedido.getProdutosDoPedido()) {
            ProdutoEntity produto = pedidoProduto.getProdutoEntity();
            produtosDoPedido += "<br>"+pedidoProduto.getQuantidade()+ "x "+ produto.getDescricao() + ":                                            " +
                    "    R$ " + formatter.format(produto.getValorUnitario().multiply(BigDecimal.valueOf(pedidoProduto.getQuantidade())));
        }

        helper.setFrom(remetente);
        helper.setTo(cliente.getEmail());
        helper.setSubject("Informações do novo pedido");

        DateTimeFormatter formatData = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Template template = configuration.getTemplate("email-template.ftl");
        Map<String, Object> dados = new HashMap<>();
        dados.put("nomeUsuario", cliente.getNome());
        dados.put("produtos",produtosDoPedido);
        dados.put("valorTotal", "R$ " + formatter.format(pedido.getValorTotal()));
        dados.put("data", pedido.getData().format(formatData));
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);

        helper.setText(html, true);

        emailSender.send(mimeMessage);
    }


}
