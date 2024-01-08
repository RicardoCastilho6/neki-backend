package com.nekiprojeto.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekiprojeto.backend.dto.UsuarioSkillRequestDTO;
import com.nekiprojeto.backend.entity.SkillEntity;
import com.nekiprojeto.backend.entity.UsuarioEntity;
import com.nekiprojeto.backend.entity.UsuarioSkillEntity;
import com.nekiprojeto.backend.repository.SkillRepository;
import com.nekiprojeto.backend.repository.UsuarioRepository;
import com.nekiprojeto.backend.repository.UsuarioSkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioSkillService {

    @Autowired
    private UsuarioSkillRepository usuarioSkillRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SkillRepository skillRepository;

   
    public UsuarioSkillService(UsuarioSkillRepository usuarioSkillRepository) {
        this.usuarioSkillRepository = usuarioSkillRepository;
    }

    public List<UsuarioSkillEntity> listarTodasUserSkillsPorIdUsuario(Long idUsuario) {
        return usuarioSkillRepository.findByUsuarioId(idUsuario);
    }


    public void associarSkillAoUsuario(UsuarioSkillRequestDTO associarSkillDTO) {
       
        UsuarioEntity usuario = usuarioRepository.findById(associarSkillDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    
        SkillEntity skill = skillRepository.findById(associarSkillDTO.getSkillId())
                .orElseThrow(() -> new RuntimeException("Skill não encontrada"));
    
        
        if (usuarioSkillRepository.findByUsuarioAndSkill(usuario, skill).isPresent()) {
            throw new RuntimeException("Associação entre usuário e skill já existe");
        }
    
        
        UsuarioSkillEntity usuarioSkill = new UsuarioSkillEntity();
        usuarioSkill.setUsuario(usuario);
        usuarioSkill.setSkill(skill);
        usuarioSkill.setLevel(associarSkillDTO.getLevel());
    
        
        usuarioSkillRepository.save(usuarioSkill);
    }

    public void atualizarLevelDaSkill(UsuarioSkillRequestDTO associarSkillDTO) {
        
        Optional<UsuarioSkillEntity> optionalUsuarioSkill = usuarioSkillRepository.findById(associarSkillDTO.getId());
        
        if (optionalUsuarioSkill.isPresent()) {
           
            UsuarioSkillEntity usuarioSkill = optionalUsuarioSkill.get();
            usuarioSkill.setLevel(associarSkillDTO.getLevel());
    
            
            usuarioSkillRepository.save(usuarioSkill);
        } else {
            throw new RuntimeException("Associação entre usuário e skill não encontrada");
        }
    }

    public void excluirAssociacaoSkillUsuario(Long usuarioSkillId) {
        
        Optional<UsuarioSkillEntity> optionalUsuarioSkill = usuarioSkillRepository.findById(usuarioSkillId);
        if (optionalUsuarioSkill.isPresent()) {
           
            usuarioSkillRepository.delete(optionalUsuarioSkill.get());
        } else {
            throw new RuntimeException("Associação entre usuário e skill não encontrada");
        }
    }
}