package com.simulador.mapper;

import com.simulador.dto.MockDefinitionRequestDTO;
import com.simulador.dto.MockDefinitionResponseDTO;
import com.simulador.model.MockDefinition;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MockMapper {

    public MockDefinition toEntity(MockDefinitionRequestDTO dto) {
        if (dto == null) return null;
        MockDefinition entity = new MockDefinition();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setMethod(dto.getMethod());
        entity.setPath(dto.getPath());
        entity.setResponseBody(dto.getResponseBody());
        entity.setRequestBody(dto.getRequestBody());
        entity.setStatusCode(dto.getStatusCode());
        entity.setDelayMs(dto.getDelayMs());
        return entity;
    }

    public MockDefinitionResponseDTO toDTO(MockDefinition entity) {
        if (entity == null) return null;
        MockDefinitionResponseDTO dto = new MockDefinitionResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setMethod(entity.getMethod());
        dto.setPath(entity.getPath());
        dto.setResponseBody(entity.getResponseBody());
        dto.setRequestBody(entity.getRequestBody());
        dto.setStatusCode(entity.getStatusCode());
        dto.setDelayMs(entity.getDelayMs());
        return dto;
    }

    public List<MockDefinitionResponseDTO> toDTOList(List<MockDefinition> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
