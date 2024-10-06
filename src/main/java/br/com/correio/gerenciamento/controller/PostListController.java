package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.postlist.DTO.DetailsPostListItemDTO;
import br.com.correio.gerenciamento.domain.postlist.DTO.CreatePostListDTO;
import br.com.correio.gerenciamento.domain.postlist.DTO.MakeMultiOut;
import br.com.correio.gerenciamento.domain.postlist.DTO.UpdatePostListItemDTO;
import br.com.correio.gerenciamento.domain.postlist.PostListItem;
import br.com.correio.gerenciamento.domain.postlist.PostListItemRepository;
import br.com.correio.gerenciamento.service.postListItem.PostListItemService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/postlist")
public class PostListController {

    @Autowired
    PostListItemRepository repository;

    PostListItemService service = new PostListItemService();

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsPostListItemDTO> create(@RequestBody @Valid CreatePostListDTO dto){

        PostListItem postListItem = new PostListItem(dto);

        repository.save(postListItem);

        DetailsPostListItemDTO dtoReturn = new DetailsPostListItemDTO(postListItem);

        return ResponseEntity.ok(dtoReturn);
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

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<DetailsPostListItemDTO> update(@PathVariable Long id,
            @RequestBody @Valid UpdatePostListItemDTO dto){

        PostListItem postListItem = repository.getReferenceById(id);

        service.updatePostList(postListItem, dto);

        DetailsPostListItemDTO dtoResponse = new DetailsPostListItemDTO(postListItem);

        return ResponseEntity.ok(dtoResponse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        PostListItem postListItem = repository.getReferenceById(id);
        repository.delete(postListItem);

        return ResponseEntity.noContent().build();
    }

//    ==============================METODOS DE ALTERAÇÃO======================================

    @GetMapping("/makeout/{id}")
    @Transactional
    public void makeOut(@PathVariable Long id){
        PostListItem postListItem = repository.getReferenceById(id);

        service.makeOut(postListItem);
    }

    @PostMapping("/makeout/out")
    @Transactional
    public void makeMultiOut(@RequestBody @Valid MakeMultiOut dto){
        for(Long id : dto.ids()){
            PostListItem postListItem = repository.getReferenceById(id);
            service.makeOut(postListItem);
        }
    }

    @GetMapping("/pendent")
    public ResponseEntity<List<DetailsPostListItemDTO>> listNotOut(){
        List<PostListItem> postListItemList = repository.getAllByOutFalse();
        List<DetailsPostListItemDTO> dtos =
            postListItemList.stream().map(DetailsPostListItemDTO::new).toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/allout")
    public ResponseEntity<List<DetailsPostListItemDTO>> listAllOut(){
        List<PostListItem> postListItemList = repository.getAllByOutTrue();
        List<DetailsPostListItemDTO> dtos =
                postListItemList.stream().map(DetailsPostListItemDTO::new).toList();

        return ResponseEntity.ok(dtos);
    }
}
