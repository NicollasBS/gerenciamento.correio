package br.com.correio.gerenciamento.domain.user;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);

    User getUserByLogin(String login);
}