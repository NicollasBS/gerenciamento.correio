package br.com.correio.gerenciamento.controller;


import br.com.correio.gerenciamento.infra.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdministratorController {

    @Autowired
    AuthService roleService;


}
