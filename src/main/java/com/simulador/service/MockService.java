package com.simulador.service;

import com.simulador.dto.MockDefinitionRequestDTO;
import com.simulador.dto.MockDefinitionResponseDTO;

import java.util.List;

public interface MockService {
    List<MockDefinitionResponseDTO> getAll();
    MockDefinitionResponseDTO create(MockDefinitionRequestDTO requestDTO);
    void delete(Long id);
}
