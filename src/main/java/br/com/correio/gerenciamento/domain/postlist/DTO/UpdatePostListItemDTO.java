package br.com.correio.gerenciamento.domain.postlist.DTO;

public record UpdatePostListItemDTO(
        String sender,
        String recipient,
        String trackingCode
) {
}
