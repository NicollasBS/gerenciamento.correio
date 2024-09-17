package br.com.correio.gerenciamento.domain.packs.DTO;

import br.com.correio.gerenciamento.domain.packs.Pack;
import br.com.correio.gerenciamento.domain.packs.oms.OMS.OMS;

import java.time.LocalDate;

public record DetailsPackDTO(
        Long id,
        String sender,
        String recipient,
        OMS om,
        String trackingCode,
        LocalDate arrivalDay,
        LocalDate deliveryDay,
        String deliveredTo,
        boolean delivered
) {

    public DetailsPackDTO(Pack p) {
        this(p.getId(), p.getSender(), p.getRecipient(), p.getOm(),
        p.getTrackingCode(), p.getArrivalDay(), p.getDeliveryDay(),
        p.getDeliveredTo(), p.isDelivered());
    }
}
