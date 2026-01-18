package com.simulador.service;

import com.simulador.dto.RequestLogResponseDTO;
import java.util.List;

public interface TrafficService {
    List<RequestLogResponseDTO> getRecentLogs();
    void clearLogs();
}
