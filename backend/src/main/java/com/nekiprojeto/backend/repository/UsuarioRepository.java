package com.nekiprojeto.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.nekiprojeto.backend.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UserDetails findByLogin(String login);
}
