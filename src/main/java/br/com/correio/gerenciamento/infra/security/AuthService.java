package br.com.correio.gerenciamento.infra.security;

import br.com.correio.gerenciamento.domain.user.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AuthService {

    public String getLoginFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null){
            return authentication.getName();
        }
        return "";
    }

    public boolean isAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()){
            return authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        }
        return false;
    }

    public boolean isDev(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()){
            return authentication.getAuthorities().stream()
                    .anyMatch(authority -> authority.getAuthority().equals("ROLE_DEV"));
        }
        return false;
    }
}
