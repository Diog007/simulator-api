package com.simulador.controller;

import com.simulador.dto.RequestLogResponseDTO;
import com.simulador.service.TrafficService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/traffic")
public class TrafficController {
    
    private final TrafficService service;

    public TrafficController(TrafficService service) {
        this.service = service;
    }

    @GetMapping
    public List<RequestLogResponseDTO> getRecentLogs() {
        return service.getRecentLogs();
    }

    @DeleteMapping
    public void clearLogs() {
        service.clearLogs();
    }
}
