package br.com.correio.gerenciamento.controller;

import br.com.correio.gerenciamento.domain.user.DTO.LoginResponseDTO;
import br.com.correio.gerenciamento.domain.user.DTO.RegisterDTO;
import br.com.correio.gerenciamento.domain.user.DTO.LoginDTO;
import br.com.correio.gerenciamento.domain.user.User;
import br.com.correio.gerenciamento.domain.user.UserRepository;
import br.com.correio.gerenciamento.infra.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO loginDTO){
        //validando usuário e senha ->
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.password());

        //autenticando o usuário
        var auth = authenticationManager.authenticate(usernamePassword);

        //gerando o token de acesso do usuário
        var token = tokenService.generateToken((User) auth.getPrincipal());

        //retorna o token como um JSON
        return ResponseEntity.ok(new LoginResponseDTO(token));
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
