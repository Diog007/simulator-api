package com.simulador.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class RequestLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String method;
    private String path;
    
    @Lob
    private String requestBody;
    
    @Lob
    private String responseBody;
    
    private int responseStatus;
    
    private Long matchedMockId; // Null if no match found
    private String mockName;
}
