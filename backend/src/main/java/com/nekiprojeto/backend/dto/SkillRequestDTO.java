package com.nekiprojeto.backend.dto;

public class SkillRequestDTO {
    private String imgUrl;
    private String descricao;
    private String nome;

   

    public SkillRequestDTO() {
    }

    public SkillRequestDTO(String imgUrl, String descricao, String nome) {
        this.imgUrl = imgUrl;
        this.descricao = descricao;
        this.nome = nome;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
