package br.com.correio.gerenciamento.service.postListItem;

import br.com.correio.gerenciamento.domain.postlist.DTO.UpdatePostListItemDTO;
import br.com.correio.gerenciamento.domain.postlist.PostListItem;

import java.time.LocalDate;

public class PostListItemService {

    public void updatePostList(PostListItem pli, UpdatePostListItemDTO dto){

        if(!dto.recipient().isBlank()) {
            pli.setRecipient(dto.recipient());
        }
        if(!dto.sender().isBlank()){
            pli.setSender(dto.sender());
        }
        if(!dto.trackingCode().isBlank()){
            pli.setTrackingCode(dto.trackingCode());
        }
        if(!dto.cep().isBlank()){
            pli.setCep(dto.cep());
        }
        pli.setAr(dto.ar());
        if(dto.description() != null){
            pli.setDescription(dto.description());
        }
    }

    public void makeOut(PostListItem pli){
        pli.setDepartureDay(LocalDate.now());
        pli.setOut(true);
    }

    public void doReturnItem(PostListItem pli){
        pli.setDepartureDay(null);
        pli.setOut(false);
    }
}
