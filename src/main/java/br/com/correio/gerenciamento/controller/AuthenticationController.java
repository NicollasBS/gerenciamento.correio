package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.user.DTO.RegisterDTO;
import br.com.correio.gerenciamento.domain.user.DTO.loginDTO.LoginDTO;
import br.com.correio.gerenciamento.domain.user.User;
import br.com.correio.gerenciamento.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        //validando usuário e senha
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());

        //autenticando o usuário
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        //verificar a existencia
        var user = repository.getUserByLogin(registerDTO.login());
        if (user != null){
            return ResponseEntity.badRequest().build();
        }

        //criando a HASH da senha
        String encrypitedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());

        User newUser = new User(registerDTO.login(), encrypitedPassword, registerDTO.role());

        repository.save(newUser);

        return ResponseEntity.ok().build();

    }
}
