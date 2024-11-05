package br.com.correio.gerenciamento.domain.logs.Users.DTO;

import br.com.correio.gerenciamento.domain.logs.Users.UserLog;
import br.com.correio.gerenciamento.domain.logs.Users.enums.OperationLogin;

import java.time.LocalDateTime;

public record UserLoginLogDTO(
        String login,
        OperationLogin operationLogin,
        LocalDateTime loginDateTime
) {
    public UserLoginLogDTO(UserLog userLog){
        this(userLog.getLogin(), userLog.getOperation(), userLog.getLoginDateTime());
    }
}
