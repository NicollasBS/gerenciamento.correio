package br.com.correio.gerenciamento.domain.postlist.DTO;

import br.com.correio.gerenciamento.domain.OMS.OMS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostListDTO(
        @NotBlank
        String sender,
        @NotBlank
        String recipient,
        @NotBlank
        String trackingCode,
        @NotNull
        OMS om,
        @NotNull
        boolean ar,
        @NotNull
        String cep,
        String description
) {
}
