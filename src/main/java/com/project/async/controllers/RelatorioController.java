package com.project.async.controllers;

import com.project.async.dto.RelatorioRequestDTO;
import com.project.async.producer.RelatorioProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.Duration;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RelatorioController {

    private final RelatorioProducer producer;
    private final StringRedisTemplate redis;

    @MutationMapping
    public String solicitarRelatorio(@Argument RelatorioRequestDTO request) {
       String protocolo = UUID.randomUUID().toString();
       log.info("Obtendo o status do microservice");

       redis.opsForValue().set("relatorio:" + protocolo, "PROCESSANDO", Duration.ofHours(24));
       producer.enviarPedidoRelatorio(protocolo, request);
       return protocolo;
    }

    @QueryMapping
    public String consultarStatus(@Argument String protocolo){
        String status = redis.opsForValue().get("relatorio:" + protocolo);
        return status != null ? status : "NAO_ENCONTRADO";
    }


}
