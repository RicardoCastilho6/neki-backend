package com.nekiprojeto.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nekiprojeto.backend.entity.CustomRole;
import com.nekiprojeto.backend.entity.UsuarioEntity;
import com.nekiprojeto.backend.repository.UsuarioRepository;
import com.nekiprojeto.backend.dto.UsuarioRequest;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void cadastrarUsuario(UsuarioRequest usuarioRequest) {
        UserDetails userDetails = usuarioRepository.findByLogin(usuarioRequest.getLogin());
        if (userDetails != null) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        String encryptPassword = new BCryptPasswordEncoder().encode(usuarioRequest.getSenha());

        
        CustomRole role = CustomRole.ADMIN;

        UsuarioEntity usuario = new UsuarioEntity(usuarioRequest.getLogin(), encryptPassword, role);
        usuarioRepository.save(usuario);
    }

    public boolean autenticar(UsuarioRequest usuarioRequest) {
       
        UserDetails userDetails = usuarioRepository.findByLogin(usuarioRequest.getLogin());

        if (userDetails != null) {
            UsuarioEntity usuario = (UsuarioEntity) userDetails;

            
            return usuarioRequest.getSenha().equals(usuario.getSenha());
        }

        return false;
    }
}
