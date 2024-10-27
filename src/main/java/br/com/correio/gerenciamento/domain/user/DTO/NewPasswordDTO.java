package br.com.correio.gerenciamento.domain.user.DTO;

import jakarta.validation.constraints.NotBlank;

public record NewPasswordDTO(
        @NotBlank
        String newPassword
) {
}
