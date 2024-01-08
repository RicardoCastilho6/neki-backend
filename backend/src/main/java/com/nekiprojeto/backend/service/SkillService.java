package com.nekiprojeto.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekiprojeto.backend.dto.SkillRequestDTO;
import com.nekiprojeto.backend.entity.SkillEntity;
import com.nekiprojeto.backend.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public void adicionarSkill(SkillRequestDTO skillRequestDTO) {
        // Cria a nova skill
        SkillEntity skill = new SkillEntity();
        skill.setNome(skillRequestDTO.getNome());
        skill.setImgUrl(skillRequestDTO.getImgUrl());
        skill.setDescricao(skillRequestDTO.getDescricao());

       
        skillRepository.save(skill);
    }


    public List<SkillEntity> listarTodasSkills() {
        
        return skillRepository.findAll();
    }

    public void excluirSkill(Long skillId) {
       
        Optional<SkillEntity> optionalSkill = skillRepository.findById(skillId);
        if (optionalSkill.isPresent()) {
           
            skillRepository.delete(optionalSkill.get());
        } else {
            throw new RuntimeException("Skill não encontrada");
        }
    }
}
