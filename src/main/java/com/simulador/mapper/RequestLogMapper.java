package com.simulador.mapper;

import com.simulador.dto.RequestLogResponseDTO;
import com.simulador.model.RequestLog;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestLogMapper {

    public RequestLogResponseDTO toDTO(RequestLog entity) {
        if (entity == null) return null;
        RequestLogResponseDTO dto = new RequestLogResponseDTO();
        dto.setId(entity.getId());
        dto.setTimestamp(entity.getTimestamp());
        dto.setMethod(entity.getMethod());
        dto.setPath(entity.getPath());
        dto.setRequestBody(entity.getRequestBody());
        dto.setResponseBody(entity.getResponseBody());
        dto.setResponseStatus(entity.getResponseStatus());
        dto.setMatchedMockId(entity.getMatchedMockId());
        dto.setMockName(entity.getMockName());
        return dto;
    }

    public List<RequestLogResponseDTO> toDTOList(List<RequestLog> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
