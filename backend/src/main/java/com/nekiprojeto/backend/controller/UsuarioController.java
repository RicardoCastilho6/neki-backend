package com.nekiprojeto.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekiprojeto.backend.base.TokenService;
import com.nekiprojeto.backend.dto.UsuarioRequest;
import com.nekiprojeto.backend.dto.UsuarioResponseDTO;
import com.nekiprojeto.backend.entity.UsuarioEntity;

import com.nekiprojeto.backend.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {

    @RequestMapping("/teste")
    public String Test(){
        return "meu primeiro projeto";
    }


    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        try {
            usuarioService.cadastrarUsuario(usuarioRequest);
            return new ResponseEntity<>("Usuário cadastrado com sucesso", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

   @PostMapping("/login")
public ResponseEntity<UsuarioResponseDTO> fazerLogin(@RequestBody UsuarioRequest loginRequestDTO) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getLogin(), loginRequestDTO.getSenha());
    var auth = this.manager.authenticate(usernamePassword);

    String token = tokenService.generateToken((UsuarioEntity)auth.getPrincipal());
    UsuarioEntity usuarioEntity = (UsuarioEntity) auth.getPrincipal();

    
    UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();
    responseDTO.setToken(token);
    responseDTO.setUsuario(loginRequestDTO.getLogin());
   responseDTO.setId(usuarioEntity.getId());

    return ResponseEntity.ok(responseDTO);
}

}
