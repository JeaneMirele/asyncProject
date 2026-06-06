package com.project.async.producer;

import com.project.async.config.RabbitMQConfig;
import com.project.async.dto.MensagemRelatorioDTO;
import com.project.async.dto.RelatorioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RelatorioProducer {
    private final RabbitTemplate rabbitTemplate;

    public void enviarPedidoRelatorio(String protocolo, RelatorioRequestDTO request) {
        MensagemRelatorioDTO msg = new MensagemRelatorioDTO(protocolo, request.usuario(), request.tipoRelatorio());

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_RELATORIOS,
                RabbitMQConfig.ROUTING_RELATORIOS,
                msg
        );
    }
}
