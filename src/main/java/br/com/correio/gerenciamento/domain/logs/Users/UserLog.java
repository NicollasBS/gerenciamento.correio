package br.com.correio.gerenciamento.domain.logs.Users;

import br.com.correio.gerenciamento.domain.logs.Users.enums.OperationLogin;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "UserLogs")
@Table(name = "users_logs")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @Enumerated(value = EnumType.STRING)
    private OperationLogin operation;

    private LocalDateTime loginDateTime;

    public UserLog(
            String login,
            OperationLogin operation
    ){
        this.login = login;
        this.operation = operation;
        this.loginDateTime = LocalDateTime.now();
    }
}
