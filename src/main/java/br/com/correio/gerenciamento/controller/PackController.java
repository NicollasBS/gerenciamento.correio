package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.OMS.OMS;
import br.com.correio.gerenciamento.domain.delivery.DeliveryPackDTO;
import br.com.correio.gerenciamento.domain.packs.DTO.CreatePackDTO;
import br.com.correio.gerenciamento.domain.packs.DTO.UpdatePackDTO;
import br.com.correio.gerenciamento.domain.packs.Pack;
import br.com.correio.gerenciamento.domain.packs.PackRepository;
import br.com.correio.gerenciamento.domain.packs.DTO.DetailsPackDTO;
import br.com.correio.gerenciamento.service.pack.PackService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/packs")
public class PackController {

    @Autowired
    private PackRepository repository;


    private final PackService service = new PackService();


    @PostMapping
    @Transactional
    public ResponseEntity<DetailsPackDTO> createPack(@RequestBody @Valid CreatePackDTO newPackDTO){


        Pack pack = new Pack(newPackDTO);

        DetailsPackDTO dto = new DetailsPackDTO(pack);


        repository.save(pack);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsPackDTO> searchPack(@PathVariable Long id){
        Pack pack = repository.getReferenceById(id);

        DetailsPackDTO packDTO = new DetailsPackDTO(pack);

        return ResponseEntity.ok(packDTO);
    }

    @GetMapping
    public ResponseEntity<List<DetailsPackDTO>> listPacks(){

        var packs = repository.findAll();
        var packsDto = packs.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(packsDto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetailsPackDTO> updatePack(@RequestBody @Valid UpdatePackDTO dto,
            @PathVariable Long id){

        Pack pack = repository.getReferenceById(id);

        PackService packService = new PackService();
        packService.updatePack(pack, dto);

        DetailsPackDTO responseDto = new DetailsPackDTO(pack);

        return ResponseEntity.ok(responseDto);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePack(@PathVariable Long id){
        Pack pack = repository.getReferenceById(id);

        repository.delete(pack);


        return ResponseEntity.noContent().build();
    }

//    funcionalidade de delivery dos packs

    @Transactional
    @PatchMapping("/delivery/{id}")
    public void doDeliveryPack(@PathVariable Long id, @RequestBody  @Valid DeliveryPackDTO dto){

        Pack pack = repository.getReferenceById(id);

        service.doDeliveryPack(pack, dto.deliveredTo());

    }

    @Transactional
    @PostMapping("/delivery/multi")
    public void doDeliveryMultiPacks(@RequestBody @Valid DeliveryPackDTO.DeliveryMultiPacksDTO dto){
        for(Long id : dto.ids()){
            Pack pack = repository.getReferenceById(id);

            service.doDeliveryPack(pack, dto.deliveredTo());
        }
    }

    @GetMapping("/delivery/pedent")
    public ResponseEntity<List<DetailsPackDTO>> listNotDelivered(){
        List<Pack> packsNotDelivered = repository.findAllByDeliveredFalse();

        var packsNotDeliveredDTO = packsNotDelivered.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(packsNotDeliveredDTO);
    }

    @GetMapping("/delivery/delivered")
    public ResponseEntity<List<DetailsPackDTO>> listDelivered(){
        List<Pack> packsNotDelivered = repository.findAllByDeliveredTrue();

        var packsNotDeliveredDTO = packsNotDelivered.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(packsNotDeliveredDTO);
    }

//    ====================== SFPC ======================================
    @GetMapping("/sfpc/pendent")
    public ResponseEntity<List<DetailsPackDTO>> listSFPCNotDelivered(){
        List<Pack> pendentSFPC = repository.findByOmAndDeliveredFalse(OMS.SFPC);

        List<DetailsPackDTO> dto = pendentSFPC.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/sfpc/delivered")
    public ResponseEntity<List<DetailsPackDTO>> listSFPCDelivered(){
        List<Pack> deliveredSFPC = repository.findByOmAndDeliveredTrue(OMS.SFPC);

        List<DetailsPackDTO> dto = deliveredSFPC.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/sfpc/delivery")
    @Transactional
    public void doDeliveryMultPacksSFPC(@RequestBody @Valid DeliveryPackDTO.DeliveryMultiPacksDTO deliveryMultiPacksDTO){
        for(Long id : deliveryMultiPacksDTO.ids()){
            Pack pack = repository.getReferenceById(id);

            service.doDeliveryPack(pack, deliveryMultiPacksDTO.deliveredTo());
        }
    }

    @PostMapping("/return/{id}")
    @Transactional
    public void doReturnPack(@PathVariable Long id){
        Pack pack = repository.getReferenceById(id);

        service.doReturnPack(pack);
    }
//    ====================== RM ======================================
    @GetMapping("/5rm/pendent")
    public ResponseEntity<List<DetailsPackDTO>> list5RMNotDelivered(){
        List<Pack> pendentSFPC = repository.findByOmAndDeliveredFalse(OMS.CMDO5RM);

        List<DetailsPackDTO> dto = pendentSFPC.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/5rm/delivered")
    public ResponseEntity<List<DetailsPackDTO>> list5RMDelivered(){
        List<Pack> delivered5RM = repository.findByOmAndDeliveredTrue(OMS.CMDO5RM);

        List<DetailsPackDTO> dto = delivered5RM.stream().map(DetailsPackDTO::new).toList();

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/5rm/delivery")
    @Transactional
    public void doDeliveryMultiPack5RM(@RequestBody @Valid DeliveryPackDTO.DeliveryMultiPacksDTO deliveryMultiPacksDTO){
        for (Long id : deliveryMultiPacksDTO.ids()){
            Pack pack = repository.getReferenceById(id);

            service.doDeliveryPack(pack, deliveryMultiPacksDTO.deliveredTo());
        }
    }
}

