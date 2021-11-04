package com.dbc.trabalho_modulo_3.Restauranteapi.Config;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@ApiIgnore
public class SpringController {

    @GetMapping(value = "/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/");
    }
}
