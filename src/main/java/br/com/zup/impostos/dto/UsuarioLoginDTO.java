package br.com.zup.impostos.dto;

import lombok.Data;

@Data
public class UsuarioLoginDTO {
    private String userName;
    private String password;

    public UsuarioLoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UsuarioLoginDTO(){
    }

}
