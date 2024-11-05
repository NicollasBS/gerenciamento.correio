package br.com.correio.gerenciamento.domain.logs.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
    List<UserLog> findAllByLogin(String user);
}
