package com.simulador.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RequestLogResponseDTO {
    private Long id;
    private LocalDateTime timestamp;
    private String method;
    private String path;
    private String requestBody;
    private String responseBody;
    private int responseStatus;
    private Long matchedMockId;
    private String mockName;
}
