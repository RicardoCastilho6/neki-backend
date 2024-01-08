package com.nekiprojeto.backend.dto;



public class UsuarioResponseDTO  {
  
    private String token;
    private String usuario;
    private Long id;

    public UsuarioResponseDTO(String token, String usuario, Long id) {
        this.token = token;
        this.usuario = usuario;
        this.id = id;
    }
    
    public UsuarioResponseDTO() { 
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

      
}
