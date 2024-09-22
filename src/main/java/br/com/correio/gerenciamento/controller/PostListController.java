package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.postlist.DTO.DetailsPostListItemDTO;
import br.com.correio.gerenciamento.domain.postlist.DTO.PostListCreateDTO;
import br.com.correio.gerenciamento.domain.postlist.PostListItem;
import br.com.correio.gerenciamento.domain.postlist.PostListItemRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postlist")
public class PostListController {

    @Autowired
    PostListItemRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createNewObject(@RequestBody @Valid PostListCreateDTO dto){

        PostListItem postListItem = new PostListItem(dto);

        repository.save(postListItem);

        return ResponseEntity.ok().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DetailsPostListItemDTO> searchById(@PathVariable Long id){
        PostListItem postListItem = repository.getReferenceById(id);
        DetailsPostListItemDTO dto = new DetailsPostListItemDTO(postListItem);

        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public  ResponseEntity<List<DetailsPostListItemDTO>> searchAll(){
        List<PostListItem> postListItens = repository.findAll();
        List<DetailsPostListItemDTO> dto = postListItens.stream().map(DetailsPostListItemDTO::new).toList();
        return ResponseEntity.ok(dto);
    }
}
