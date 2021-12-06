import com.dbc.trabalho_modulo_3.Restauranteapi.dto.PedidoDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TestaPedido {

    @Test
    void testaSeValorPedidoEhZero() {

        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setIdPedido(1);
        pedidoDTO.setValorTotal(new BigDecimal("0"));
        BigDecimal num = new BigDecimal("10");

        assertEquals(num,pedidoDTO.getValorTotal());

    }

}
