package br.com.correio.gerenciamento.service.logs;

import br.com.correio.gerenciamento.domain.logs.Objects.ObjectsLog;
import br.com.correio.gerenciamento.domain.logs.Objects.ObjectsLogRepository;
import br.com.correio.gerenciamento.domain.logs.Objects.enums.ObjType;
import br.com.correio.gerenciamento.domain.logs.Objects.enums.OperationType;
import br.com.correio.gerenciamento.domain.logs.Users.UserLog;
import br.com.correio.gerenciamento.domain.logs.Users.UserLogRepository;
import br.com.correio.gerenciamento.domain.logs.Users.enums.OperationLogin;
import br.com.correio.gerenciamento.domain.packs.Pack;
import br.com.correio.gerenciamento.domain.postlist.PostListItem;
import br.com.correio.gerenciamento.infra.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private AuthService authService;

    @Autowired
    private ObjectsLogRepository objectsLogRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    public void newPackLog(Pack pack, OperationType operation){
        String user = authService.getLoginFromToken();

        ObjectsLog newObjectsLog = new ObjectsLog(user, pack.getId(), ObjType.PACK, operation);

        objectsLogRepository.save(newObjectsLog);
    }

    public void newPostListLog(PostListItem postListItem, OperationType operation){
        String user = authService.getLoginFromToken();

        ObjectsLog newObjectsLog = new ObjectsLog(user, postListItem.getId(), ObjType.POSTLIST, operation);

        objectsLogRepository.save(newObjectsLog);
    }

    public void newUserLog(String login, OperationLogin operationLogin){
        UserLog newUserLog = new UserLog(login, operationLogin);

        userLogRepository.save(newUserLog);
    }
}
