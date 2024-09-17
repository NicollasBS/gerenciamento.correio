package br.com.correio.gerenciamento.domain.packs.DTO;

import br.com.correio.gerenciamento.domain.packs.oms.OMS.OMS;

public record UpdatePackDTO(
        String sender,
        String recipient,
        OMS om,
        String trackingCode
) {
}
