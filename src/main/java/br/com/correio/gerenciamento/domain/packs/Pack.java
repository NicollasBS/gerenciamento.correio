package br.com.correio.gerenciamento.domain.packs;

import br.com.correio.gerenciamento.domain.packs.DTO.CreatePackDTO;
import br.com.correio.gerenciamento.domain.packs.oms.OMS.OMS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @Enumerated(EnumType.STRING)
    private OMS om;

    private String trackingCode;
    private LocalDate arrivalDay;
    private LocalDate deliveryDay;
    private String deliveredTo;
    private boolean delivered;

    public Pack(CreatePackDTO dto) {
        LocalDate date = LocalDate.now();

        this.sender = dto.sender();
        this.recipient = dto.recipient();
        this.om = dto.om();
        this.trackingCode = dto.trackingCode();
        this.arrivalDay = date;
        this.delivered = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public OMS getOm() {
        return om;
    }

    public void setOm(OMS om) {
        this.om = om;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public LocalDate getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(LocalDate arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public LocalDate getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(LocalDate deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    public String getDeliveredTo() {
        return deliveredTo;
    }

    public void setDeliveredTo(String deliveredTo) {
        this.deliveredTo = deliveredTo;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
