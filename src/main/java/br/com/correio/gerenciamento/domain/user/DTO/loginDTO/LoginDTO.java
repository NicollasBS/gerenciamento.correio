package br.com.correio.gerenciamento.domain.user.DTO.loginDTO;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        String login,
        @NotBlank
        String password
) { }
