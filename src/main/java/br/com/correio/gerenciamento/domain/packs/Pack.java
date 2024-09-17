package br.com.correio.gerenciamento.domain.packs;

import br.com.correio.gerenciamento.domain.packs.oms.OMS.OMS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name="packs")
@Entity(name="Pack")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pack {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String recipient;

    @Embedded
    private OMS om;

    private String trackingCode;
    private Date arrivalDay;
    private Date deliveryDay;
    private String deliveredTo;
    private boolean delivered;
}
