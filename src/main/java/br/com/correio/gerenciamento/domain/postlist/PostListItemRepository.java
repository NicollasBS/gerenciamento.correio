package br.com.correio.gerenciamento.domain.postlist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostListItemRepository extends JpaRepository<PostListItem, Long> {
    List<PostListItem> getAllByOutFalse();

    List<PostListItem> getAllByOutTrue();

}
