package br.com.correio.gerenciamento.domain.packs;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findAllByDeliveredFalse();
}
