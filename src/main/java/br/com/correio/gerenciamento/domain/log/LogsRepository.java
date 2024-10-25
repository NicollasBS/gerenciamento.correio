package br.com.correio.gerenciamento.domain.log;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Log, Long> {
}
