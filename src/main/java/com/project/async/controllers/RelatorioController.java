package com.project.async.controllers;

import com.project.async.dto.RelatorioRequestDTO;
import com.project.async.producer.RelatorioProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioProducer producer;

    @MutationMapping
    public String solicitarRelatorio(@Argument RelatorioRequestDTO request) {
       String protocolo = UUID.randomUUID().toString();

       producer.enviarPedidoRelatorio(protocolo, request);
       return protocolo;
    }
}
