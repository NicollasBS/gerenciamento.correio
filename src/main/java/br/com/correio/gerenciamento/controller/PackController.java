package br.com.correio.gerenciamento.controller;

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
    PackRepository repository;

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
}

