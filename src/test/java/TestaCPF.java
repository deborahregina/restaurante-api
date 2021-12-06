import com.dbc.trabalho_modulo_3.Restauranteapi.dto.ClienteDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.PedidoDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
public class TestaCPF {

    @Test
    void testaCPF() {
        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setCpf("12312312312");

        assertEquals(11,clienteDTO.getCpf().length());

    }


}
