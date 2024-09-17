package br.com.correio.gerenciamento.domain.packs;

import br.com.correio.gerenciamento.domain.packs.oms.OMS.OMS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePackDTO(
        @NotBlank
        String sender,
        @NotBlank
        String recipient,
        @NotNull
        OMS om,
        String trackingCode
) { }
