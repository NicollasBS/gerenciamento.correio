package br.com.correio.gerenciamento.domain.log;

import br.com.correio.gerenciamento.domain.log.enumerates.ItemType;
import br.com.correio.gerenciamento.domain.log.enumerates.Operation;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "logs")
@Entity(name = "Log")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Log {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private Long objectId;
    @Enumerated(value = EnumType.STRING)
    private ItemType type;
    @Enumerated(value = EnumType.STRING)
    private Operation operation;

    public Log(String login, Long objectId, ItemType type, Operation operation){
        this.login = login;
        this.objectId = objectId;
        this.type = type;
        this.operation = operation;
    }
}
