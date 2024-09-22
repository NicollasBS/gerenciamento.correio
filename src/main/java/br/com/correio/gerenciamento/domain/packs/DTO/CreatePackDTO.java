package br.com.correio.gerenciamento.domain.packs.DTO;

import br.com.correio.gerenciamento.domain.OMS.OMS;
import jakarta.validation.constraints.NotBlank;

public record CreatePackDTO(
        @NotBlank
        String sender,
        @NotBlank
        String recipient,
        OMS om,
        String trackingCode
) { }
