package com.dbc.trabalho_modulo_3.Restauranteapi.security;

import com.dbc.trabalho_modulo_3.Restauranteapi.entity.UsuarioEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {
    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuario = usuarioService.findByLogin(login);

        if(usuario.isPresent()){
            return usuario.get();
        }

        return null;
    }
}
