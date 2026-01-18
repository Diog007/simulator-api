package com.simulador.repository;

import com.simulador.model.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    // Get latest 50 logs
    List<RequestLog> findTop50ByOrderByTimestampDesc();
}
