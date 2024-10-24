package br.com.correio.gerenciamento.domain.user.DTO;

import br.com.correio.gerenciamento.domain.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank
        String login,
        @NotBlank
        String password,
        @NotNull
        UserRole role
) {
}
