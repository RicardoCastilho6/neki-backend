package com.nekiprojeto.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.nekiprojeto.backend.dto.UsuarioSkillRequestDTO;
import com.nekiprojeto.backend.entity.UsuarioSkillEntity;
import com.nekiprojeto.backend.service.UsuarioSkillService;

@RestController
@RequestMapping("/usuarioskills")
public class UsuarioSkillController {

    @Autowired
    private UsuarioSkillService usuarioSkillService;

    @PostMapping("/associar")
    public ResponseEntity<String> associarSkillAoUsuario(@RequestBody UsuarioSkillRequestDTO usuarioSkillRequest) {
        try {
            usuarioSkillService.associarSkillAoUsuario(usuarioSkillRequest);
            return new ResponseEntity<>("Associação entre usuário e skill adicionada com sucesso", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/atualizar/{usuarioSkillId}")
    public ResponseEntity<String> atualizarLevelDaSkill(@RequestBody UsuarioSkillRequestDTO usuarioSkillRequest) {
        try {
            usuarioSkillService.atualizarLevelDaSkill(usuarioSkillRequest);
            return new ResponseEntity<>("Level da associação entre usuário e skill atualizado com sucesso", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/excluir/{usuarioSkillId}")
    public ResponseEntity<String> excluirAssociacaoSkillUsuario(@PathVariable Long usuarioSkillId) {
        try {
            usuarioSkillService.excluirAssociacaoSkillUsuario(usuarioSkillId);
            return new ResponseEntity<>("Associação entre usuário e skill excluída com sucesso", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<UsuarioSkillEntity>> listarTodasUserSkillsPorIdUsuario(@PathVariable Long idUsuario) {
        List<UsuarioSkillEntity> skills = usuarioSkillService.listarTodasUserSkillsPorIdUsuario(idUsuario);
        return ResponseEntity.ok(skills);
    }

   
}
