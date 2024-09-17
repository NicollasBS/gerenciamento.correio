package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.packs.CreatePackDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/packs")
public class Packs {

    @PostMapping
    public ResponseEntity createPack(@RequestBody @Valid CreatePackDTO newPackDTO){



        return ResponseEntity.ok().build();
    }
}
