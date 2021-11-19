package com.dbc.trabalho_modulo_3.Restauranteapi.controller;

import com.dbc.trabalho_modulo_3.Restauranteapi.dto.LoginDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.UsuarioCreateDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.dto.UsuarioDTO;
import com.dbc.trabalho_modulo_3.Restauranteapi.entity.UsuarioEntity;
import com.dbc.trabalho_modulo_3.Restauranteapi.security.TokenService;
import com.dbc.trabalho_modulo_3.Restauranteapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UsuarioService usuarioService;

    @PostMapping
    public String auth(@RequestBody @Valid LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken user =
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsuario(),
                        loginDTO.getSenha()
                );

        Authentication authenticate = authenticationManager.authenticate(user);

        String token = tokenService.generateToken((UsuarioEntity) authenticate.getPrincipal());
        return token;
    }

    @PostMapping("/create")
    public UsuarioDTO postUsuario(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        return usuarioService.create(usuarioCreateDTO);
    }
}
