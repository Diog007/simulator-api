package com.simulador.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MockDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // New field for user-defined name
    
    // GET, POST, PUT, DELETE
    private String method; 
    
    // Example: /api/v1/users
    private String path;   
    
    @Lob
    private String responseBody;
    
    @Lob
    private String requestBody;
    
    private int statusCode;
    
    private long delayMs;
}
