package com.simulador.controller;

import com.simulador.dto.SimulationResult;
import com.simulador.service.SimulatorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulatorController {

    private final SimulatorService service;

    public SimulatorController(SimulatorService service) {
        this.service = service;
    }

    @RequestMapping("/**")
    public ResponseEntity<Object> handleRequest(HttpServletRequest request, @org.springframework.web.bind.annotation.RequestBody(required = false) String body) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        SimulationResult result = service.processRequest(method, path, body);

        ResponseEntity.BodyBuilder builder = ResponseEntity.status(result.getStatusCode());
        
        // Add default JSON content type if body is present, or properly handle headers if SimulationResult had them
        if (result.getBody() != null) {
            builder.header("Content-Type", "application/json; charset=utf-8");
            return builder.body(result.getBody());
        }
        
        return builder.build();
    }
}
