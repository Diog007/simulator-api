package com.simulador.service.impl;

import com.simulador.dto.MockDefinitionRequestDTO;
import com.simulador.dto.MockDefinitionResponseDTO;
import com.simulador.mapper.MockMapper;
import com.simulador.model.MockDefinition;
import com.simulador.repository.MockRepository;
import com.simulador.service.MockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MockServiceImpl implements MockService {

    private final MockRepository repository;
    private final MockMapper mapper;

    public MockServiceImpl(MockRepository repository, MockMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<MockDefinitionResponseDTO> getAll() {
        return mapper.toDTOList(repository.findAll());
    }

    @Override
    public MockDefinitionResponseDTO create(MockDefinitionRequestDTO requestDTO) {
        MockDefinition entity = mapper.toEntity(requestDTO);
        
        // Logic moved from Controller: Simple normalization
        if (entity.getPath() != null && !entity.getPath().startsWith("/")) {
            entity.setPath("/" + entity.getPath());
        }
        
        MockDefinition saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
