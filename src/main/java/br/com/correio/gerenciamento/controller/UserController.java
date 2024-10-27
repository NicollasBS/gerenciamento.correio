package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.logs.Users.UserLog;
import br.com.correio.gerenciamento.domain.logs.Users.enums.OperationLogin;
import br.com.correio.gerenciamento.domain.user.DTO.NewPasswordDTO;
import br.com.correio.gerenciamento.domain.user.User;
import br.com.correio.gerenciamento.domain.user.UserRepository;
import br.com.correio.gerenciamento.infra.security.AuthService;
import br.com.correio.gerenciamento.service.logs.LogService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogService logService;

    @Transactional
    @PatchMapping("/change_password")
    public ResponseEntity changePassword(@RequestBody @Valid NewPasswordDTO data){
        var login = authService.getLoginFromToken();
        System.out.println(login);

        var user = userRepository.getUserByLogin(login);

        if (user == null){
            ResponseEntity.badRequest().build();
        }

        String newPasswordEncrypted = new BCryptPasswordEncoder().encode(data.newPassword());

        if(user != null && newPasswordEncrypted != null){
            user.setPassword(newPasswordEncrypted);
        }

        logService.newUserLog(login, OperationLogin.CHANGEPASSWORD);

        return ResponseEntity.noContent().build();
    }
}
