package br.com.correio.gerenciamento.domain.packs;

import br.com.correio.gerenciamento.domain.OMS.OMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PackRepository extends JpaRepository<Pack, Long> {

    List<Pack> findAllByDeliveredFalse();

    List<Pack> findAllByDeliveredTrue();

    @Query("SELECT p FROM Pack p WHERE p.om = :om AND p.delivered = false")
    List<Pack> findByOmAndDeliveredFalse(OMS om);
}