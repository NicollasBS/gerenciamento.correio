package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.delivery.DeliveryPackDTO;
import br.com.correio.gerenciamento.domain.packs.DTO.DetailsPackDTO;
import br.com.correio.gerenciamento.domain.packs.Pack;
import br.com.correio.gerenciamento.domain.packs.PackRepository;
import br.com.correio.gerenciamento.service.pack.PackService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
@CrossOrigin(origins = "*")
public class DeliveryController {

    @Autowired
    PackRepository repository;

    PackService service = new PackService();

    @Transactional
    @PatchMapping("/{id}")
    public void doDeliveryPack(@PathVariable Long id, @RequestBody  @Valid DeliveryPackDTO dto){

        Pack pack = repository.getReferenceById(id);

        service.doDeliveryPack(pack, dto.deliveredTo());

    }

    @Transactional
    @PostMapping("/multi")
    public void doDeliveryMultiPacks(@RequestBody @Valid DeliveryPackDTO.DeliveryMultiPacksDTO dto){
        for(Long id : dto.ids()){
            Pack pack = repository.getReferenceById(id);

            service.doDeliveryPack(pack, dto.deliveredTo());
        }
    }

    @GetMapping("/pedent")
    public ResponseEntity<List<DetailsPackDTO>> listNotDelivered(){
        List<Pack> packsNotDelivered = repository.findAllByDeliveredFalse();

        var packsNotDeliveredDTO = packsNotDelivered.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(packsNotDeliveredDTO);
    }

    @GetMapping("/delivered")
    public ResponseEntity<List<DetailsPackDTO>> listDelivered(){
        List<Pack> packsNotDelivered = repository.findAllByDeliveredTrue();

        var packsNotDeliveredDTO = packsNotDelivered.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(packsNotDeliveredDTO);
    }


}
