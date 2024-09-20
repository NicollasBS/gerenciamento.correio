package br.com.correio.gerenciamento.domain.packs;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findAllByDeliveredFalse();

    List<Pack> findAllByDeliveredTrue();
}
