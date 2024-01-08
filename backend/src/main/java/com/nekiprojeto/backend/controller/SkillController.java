package com.nekiprojeto.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nekiprojeto.backend.dto.SkillRequestDTO;
import com.nekiprojeto.backend.entity.SkillEntity;
import com.nekiprojeto.backend.service.SkillService;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @RequestMapping("/teste")
    public String Test(){
        return "meu primeiro projeto";
    }

    @Autowired
    private SkillService skillService;

    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarSkill(@RequestBody SkillRequestDTO skillRequestDTO) {
        try {
            skillService.adicionarSkill(skillRequestDTO);
            return new ResponseEntity<>("Skill adicionada com sucesso", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SkillEntity>> listarTodasSkills() {
        List<SkillEntity> skills = skillService.listarTodasSkills();
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{skillId}")
    public ResponseEntity<String> excluirSkill(@PathVariable Long skillId) {
        try {
            skillService.excluirSkill(skillId);
            return new ResponseEntity<>("Skill excluída com sucesso", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
