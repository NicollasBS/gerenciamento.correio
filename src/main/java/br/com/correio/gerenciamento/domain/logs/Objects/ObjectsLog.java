package br.com.correio.gerenciamento.domain.logs.Objects;

import br.com.correio.gerenciamento.domain.logs.Objects.enums.ObjType;
import br.com.correio.gerenciamento.domain.logs.Objects.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "objects_logs")
@Entity(name = "ObjectLog")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ObjectsLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private Long objectId;
    @Enumerated(value = EnumType.STRING)
    private ObjType type;
    @Enumerated(value = EnumType.STRING)
    private OperationType operation;

    private LocalDateTime atDateTime;

    public ObjectsLog(
            String login, Long objectId, ObjType type, OperationType operation
    ){
        this.login = login;
        this.objectId = objectId;
        this.type = type;
        this.operation = operation;
        this.atDateTime = LocalDateTime.now();
    }
}
