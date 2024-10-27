package br.com.correio.gerenciamento.domain.logs.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
