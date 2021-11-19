package com.dbc.trabalho_modulo_3.Restauranteapi.config;

import com.dbc.trabalho_modulo_3.Restauranteapi.exception.RegraDeNegocioException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.rmi.UnexpectedException;

public class SimpleErrorDecode implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        Response.Body body = response.body();
        if (body == null){
            return new UnexpectedException("Erro inesperado");
        }

        try {
            String bodyString = IOUtils.toString(body.asInputStream());
            switch (response.status()){
                case 400:
                    return new RegraDeNegocioException(bodyString);
                default:
                    return new Exception("Erro genérico");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        }
    }
}
