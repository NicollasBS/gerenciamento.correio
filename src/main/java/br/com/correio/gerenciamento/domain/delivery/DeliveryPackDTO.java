package br.com.correio.gerenciamento.domain.delivery;

import jakarta.validation.constraints.NotBlank;

public record DeliveryPackDTO(
        @NotBlank
        String deliveredTo
) {
}
