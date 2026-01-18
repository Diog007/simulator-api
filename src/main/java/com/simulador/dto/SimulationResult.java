package com.simulador.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class SimulationResult {
    private int statusCode;
    private String body;
    private Map<String, String> headers;
}
