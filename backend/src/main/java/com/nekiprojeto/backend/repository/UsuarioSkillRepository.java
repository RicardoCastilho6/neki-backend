package com.nekiprojeto.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nekiprojeto.backend.entity.UsuarioEntity;
import com.nekiprojeto.backend.entity.SkillEntity;
import com.nekiprojeto.backend.entity.UsuarioSkillEntity;

public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkillEntity, Long> {

    Optional<UsuarioSkillEntity> findByUsuarioAndSkill(UsuarioEntity usuario, SkillEntity skill);


    List<UsuarioSkillEntity> findByUsuarioId(Long idUsuario);
}

