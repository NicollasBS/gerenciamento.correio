package br.com.correio.gerenciamento.domain.delivery;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DeliveryPackDTO(
        @NotBlank
        String deliveredTo
) {
        public record DeliveryMultiPacksDTO(
                List<Long> ids,
                String deliveredTo
        ) {
        }
}
