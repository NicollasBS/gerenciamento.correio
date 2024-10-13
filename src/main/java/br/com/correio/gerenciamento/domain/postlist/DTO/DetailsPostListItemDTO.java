package br.com.correio.gerenciamento.domain.postlist.DTO;

import br.com.correio.gerenciamento.domain.OMS.OMS;
import br.com.correio.gerenciamento.domain.postlist.PostListItem;

import java.time.LocalDate;

public record DetailsPostListItemDTO(
        Long id,
        String sender,
        String recipient,
        String trackingCode,
        OMS om,
        LocalDate arrivalDay,
        LocalDate departureDay,
        boolean out,
        boolean ar,
        String cep,
        String description
) {
    public DetailsPostListItemDTO(PostListItem postListItem){
        this(postListItem.getId(), postListItem.getSender(), postListItem.getRecipient(), postListItem.getTrackingCode(), postListItem.getOm(),
        postListItem.getArrivalDay(), postListItem.getDepartureDay(), postListItem.isOut(), postListItem.isAr(), postListItem.getCep(), postListItem.getDescription());
    }
}
