package br.com.correio.gerenciamento.domain.postlist;

import br.com.correio.gerenciamento.domain.OMS.OMS;
import br.com.correio.gerenciamento.domain.postlist.DTO.PostListCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "post_list_itens")
@Entity(name = "PostListItem")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PostListItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String recipient;
    private String trackingCode;
    @Enumerated
    private OMS om;
    private LocalDate arrivalDay;
    private LocalDate departureDay;
    @Column(name = "out_verify")
    private boolean out;

    public PostListItem(PostListCreateDTO dto){
        this.sender = dto.sender();
        this.recipient = dto.recipient();
        this.trackingCode = dto.trackingCode();
        this.om = dto.om();
        this.arrivalDay = LocalDate.now();
        this.out = false;
    }
}
