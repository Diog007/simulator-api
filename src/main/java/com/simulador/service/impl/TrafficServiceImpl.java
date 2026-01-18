package com.simulador.service.impl;

import com.simulador.dto.RequestLogResponseDTO;
import com.simulador.mapper.RequestLogMapper;
import com.simulador.repository.RequestLogRepository;
import com.simulador.service.TrafficService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficServiceImpl implements TrafficService {

    private final RequestLogRepository repository;
    private final RequestLogMapper mapper;

    public TrafficServiceImpl(RequestLogRepository repository, RequestLogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<RequestLogResponseDTO> getRecentLogs() {
        return mapper.toDTOList(repository.findTop50ByOrderByTimestampDesc());
    }

    @Override
    public void clearLogs() {
        repository.deleteAll();
    }
}
