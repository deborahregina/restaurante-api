package com.dbc.trabalho_modulo_3.Restauranteapi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PEDIDO")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PEDIDO")
    @SequenceGenerator(name = "SEQ_PEDIDO", sequenceName = "SEQ_PEDIDO", allocationSize = 1)
    @Column(name = "ID_PEDIDO")
    private Integer idPedido;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private ClienteEntity clienteEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "pedidoEntity", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<PedidoProdutoEntity> produtosDoPedido;

    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DATA_PEDIDO")
    private LocalDateTime data;


}

