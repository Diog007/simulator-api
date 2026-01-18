package com.simulador.controller;

import com.simulador.dto.MockDefinitionRequestDTO;
import com.simulador.dto.MockDefinitionResponseDTO;
import com.simulador.service.MockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/mocks")
@CrossOrigin(origins = "*")
public class MockController {
    
    private final MockService service;
    
    public MockController(MockService service) {
        this.service = service;
    }

    @GetMapping
    public List<MockDefinitionResponseDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    public MockDefinitionResponseDTO create(@RequestBody MockDefinitionRequestDTO request) {
        return service.create(request);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
