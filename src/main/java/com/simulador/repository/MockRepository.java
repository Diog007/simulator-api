package com.simulador.repository;

import com.simulador.model.MockDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MockRepository extends JpaRepository<MockDefinition, Long> {
    Optional<MockDefinition> findByMethodAndPath(String method, String path);
}
