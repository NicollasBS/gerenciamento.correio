package br.com.correio.gerenciamento.domain.packs;

import br.com.correio.gerenciamento.domain.packs.DTO.CreatePackDTO;
import br.com.correio.gerenciamento.domain.OMS.OMS;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name="packs")
@Entity(name="Pack")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
@Getter
public class Pack {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sender;
    private String recipient;

    @Enumerated(EnumType.STRING)
    private OMS om;

    private String trackingCode;
    private LocalDate arrivalDay;

    private LocalDate deliveryDay;
    private String deliveredTo;
    private boolean delivered;

    private String description;


    public Pack(CreatePackDTO dto) {
        LocalDate date = LocalDate.now();

        this.sender = dto.sender();
        this.recipient = dto.recipient();
        this.om = dto.om();
        this.trackingCode = dto.trackingCode();
        this.arrivalDay = date;
        this.delivered = false;
        this.description = dto.description();
    }


}
