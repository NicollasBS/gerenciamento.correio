package br.com.correio.gerenciamento.domain.logs.Objects.DTO;

import br.com.correio.gerenciamento.domain.logs.Objects.ObjectsLog;
import br.com.correio.gerenciamento.domain.logs.Objects.enums.ObjType;
import br.com.correio.gerenciamento.domain.logs.Objects.enums.OperationType;

import java.time.LocalDateTime;

public record ObjectsLogDTO(
        String login,
        Long objectId,
        ObjType type,
        OperationType operation,
        LocalDateTime datetime
) {
    public ObjectsLogDTO(ObjectsLog objectsLog){
        this(objectsLog.getLogin(), objectsLog.getObjectId(), objectsLog.getType(), objectsLog.getOperation(), objectsLog.getAtDateTime());
    }
}
