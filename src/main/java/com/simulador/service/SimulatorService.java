package com.simulador.service;

import com.simulador.dto.SimulationResult;

public interface SimulatorService {
    SimulationResult processRequest(String method, String path, String body);
}
