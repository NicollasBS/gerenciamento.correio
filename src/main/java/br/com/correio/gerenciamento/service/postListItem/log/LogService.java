package br.com.correio.gerenciamento.service.postListItem.log;

import br.com.correio.gerenciamento.domain.log.Log;
import br.com.correio.gerenciamento.domain.log.LogsRepository;
import br.com.correio.gerenciamento.domain.log.enumerates.ItemType;
import br.com.correio.gerenciamento.domain.log.enumerates.Operation;
import br.com.correio.gerenciamento.domain.packs.Pack;
import br.com.correio.gerenciamento.domain.postlist.PostListItem;
import br.com.correio.gerenciamento.domain.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class LogService {

    @Autowired
    private LogsRepository repository;

    @Transactional
    public void saveLogPack(User user, Pack pack, Operation operation){
        Log newPackLog = new Log(user.getLogin(), pack.getId(), ItemType.PACK, operation);

        repository.save(newPackLog);
    }

    public void saveLogPostList(User user, PostListItem postListItem, Operation operation){
        Log newPostListLog = new Log(user.getLogin(), postListItem.getId(), ItemType.POST_LIST_ITEM, operation);

        repository.save(newPostListLog);
    }
}
