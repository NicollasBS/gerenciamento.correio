package br.com.correio.gerenciamento.domain.logs.Objects;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectsLogRepository extends JpaRepository<ObjectsLog, Long> {
    List<ObjectsLog> findAllByLogin(String user);
}
