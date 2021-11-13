package com.dbc.trabalho_modulo_3.Restauranteapi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dbc.trabalho_modulo_3.Restauranteapi"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("Restaurante API")
                        .description("Trabalho final módulo 3.2")
                        .version("2.0.0")
                        .license("Apache 2.0")
                        .contact(new Contact("Adriele, Camile, Deborah e Guilherme","www.dbccompany.com.br","deborah.regina@dbccompany.com"))
                        .build());
    }
}
