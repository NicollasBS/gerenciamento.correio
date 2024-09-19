package br.com.correio.gerenciamento.service.pack;

import br.com.correio.gerenciamento.domain.delivery.DeliveryPackDTO;
import br.com.correio.gerenciamento.domain.packs.DTO.UpdatePackDTO;
import br.com.correio.gerenciamento.domain.packs.Pack;

import java.time.LocalDate;

public class PackService {

    public void updatePack(Pack pack, UpdatePackDTO dto){
        if(!dto.sender().isBlank()){
            pack.setSender(dto.sender());
        }
        if(!dto.recipient().isBlank()){
            pack.setRecipient(dto.recipient());
        }
        if(dto.om() != null){
            pack.setOm(dto.om());
        }
        if(!dto.trackingCode().isBlank()){
            pack.setTrackingCode(dto.trackingCode());
        }
    }

    public void doDeliveryPack(Pack pack, DeliveryPackDTO dto){
        pack.setDeliveredTo(dto.deliveredTo());
        pack.setDeliveryDay(LocalDate.now());
        pack.setDelivered(true);
    }
}
