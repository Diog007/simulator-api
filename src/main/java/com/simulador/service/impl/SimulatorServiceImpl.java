package com.simulador.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulador.dto.SimulationResult;
import com.simulador.model.MockDefinition;
import com.simulador.model.RequestLog;
import com.simulador.repository.MockRepository;
import com.simulador.repository.RequestLogRepository;
import com.simulador.service.SimulatorService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SimulatorServiceImpl implements SimulatorService {

    private final MockRepository repository;
    private final RequestLogRepository logRepository;
    private final ObjectMapper mapper;

    public SimulatorServiceImpl(MockRepository repository, RequestLogRepository logRepository, ObjectMapper mapper) {
        this.repository = repository;
        this.logRepository = logRepository;
        this.mapper = mapper;
    }

    @Override
    public SimulationResult processRequest(String method, String path, String body) {
        RequestLog log = new RequestLog();
        log.setTimestamp(LocalDateTime.now());
        log.setMethod(method);
        log.setPath(path);
        log.setRequestBody(body);
        
        Optional<MockDefinition> mockOpt = repository.findByMethodAndPath(method, path);
        
        if (mockOpt.isPresent()) {
            MockDefinition mock = mockOpt.get();
            log.setMatchedMockId(mock.getId());
            log.setMockName(mock.getName());
            
            // Validate Request Body if configured
            if (mock.getRequestBody() != null && !mock.getRequestBody().isBlank()) {
                if (body == null || body.isBlank()) {
                    return createErrorResponse(log, 400, 
                        "{\"erro\": \"Corpo da requisição ausente\", \"mensagem\": \"Este mock espera receber um JSON específico, mas você não enviou nada.\"}");
                }
                
                try {
                    JsonNode expected = mapper.readTree(mock.getRequestBody());
                    JsonNode actual = mapper.readTree(body);
                    
                    if (!expected.equals(actual)) {
                        return createErrorResponse(log, 400,
                            "{\"erro\": \"Divergência de Dados\", \"mensagem\": \"O JSON enviado no Postman não corresponde ao JSON esperado definido no Mock.\", \"dica\": \"Verifique se os campos e valores estão idênticos aos configurados.\"}");
                    }
                } catch (Exception e) {
                    if (!mock.getRequestBody().trim().equals(body.trim())) {
                        return createErrorResponse(log, 400,
                             "{\"erro\": \"Conteúdo Diferente\", \"mensagem\": \"O texto enviado não corresponde exatamente ao esperado (verifique espaços ou formatação).\"}");
                    }
                }
            }
            
            // Artificial Delay
            if (mock.getDelayMs() > 0) {
                try {
                    Thread.sleep(mock.getDelayMs());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            
            log.setResponseStatus(mock.getStatusCode());
            log.setResponseBody(mock.getResponseBody());
            logRepository.save(log);
            
            return SimulationResult.builder()
                    .statusCode(mock.getStatusCode())
                    .body(mock.getResponseBody())
                    // Headers can be added if MockDefinition supports them later, for now defaulting to JSON
                    .build();
        }
        
        log.setResponseStatus(404);
        logRepository.save(log);
        return SimulationResult.builder()
                .statusCode(404)
                .build();
    }

    private SimulationResult createErrorResponse(RequestLog log, int status, String responseBody) {
        log.setResponseStatus(status);
        log.setResponseBody(responseBody);
        logRepository.save(log);
        return SimulationResult.builder()
                .statusCode(status)
                .body(responseBody)
                .build();
    }
}
