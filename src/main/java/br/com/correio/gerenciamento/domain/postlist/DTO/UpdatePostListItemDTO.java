package br.com.correio.gerenciamento.domain.postlist.DTO;

import br.com.correio.gerenciamento.domain.OMS.OMS;

public record UpdatePostListItemDTO(
        String sender,
        String recipient,
        String trackingCode,
        OMS om,
        String cep
) {
}
