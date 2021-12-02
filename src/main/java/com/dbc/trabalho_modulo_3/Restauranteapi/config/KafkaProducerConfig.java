package com.dbc.trabalho_modulo_3.Restauranteapi.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.bootstrap-server}")
    private String bootstrapServer;

    private <T> ProducerFactory<String, T> genericProducerFactory(Class<? extends Serializer<T>> clazz) {

        Map<String, Object> propriedades = new HashMap<>();

        propriedades.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        propriedades.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        propriedades.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, clazz);

        return new DefaultKafkaProducerFactory<>(propriedades);
    }

    @Bean
    public KafkaTemplate<String, String> stringKafkaTemplate() {
        return new KafkaTemplate<>(genericProducerFactory(StringSerializer.class));
    }
}