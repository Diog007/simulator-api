package com.simulador.dto;

import lombok.Data;

@Data
public class MockDefinitionResponseDTO {
    private Long id;
    private String name;
    private String method;
    private String path;
    private String responseBody;
    private String requestBody;
    private int statusCode;
    private long delayMs;
}
