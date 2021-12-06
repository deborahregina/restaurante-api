import com.dbc.trabalho_modulo_3.Restauranteapi.dto.ClienteDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.EnderecoDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.PedidoDTO;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;

public class TestaCEP {

    @Test
    void testaCEP() {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setCep("45645678");

        assertEquals(8,enderecoDTO.getCep().length());
    }
}
