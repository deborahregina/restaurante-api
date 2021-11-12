package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PEDIDO")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PEDIDO")
    @SequenceGenerator(name = "SEQ_PEDIDO", sequenceName = "SEQ_PEDIDO", allocationSize = 1)

    @Column(name = "ID_PEDIDO")
    private Integer idPedido;
    @Column(name = "ID_CLIENTE")
    private Integer idCliente;

    @JsonIgnore
    @ManyToMany

    @JoinTable(name = "PEDIDO_PRODUTO", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<PedidoProdutoEntity> produtosDoPedido;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATA")
    private LocalDateTime data;


}

