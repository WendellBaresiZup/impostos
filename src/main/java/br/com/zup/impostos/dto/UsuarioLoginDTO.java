package br.com.zup.impostos.dto;

public class UsuarioLoginDTO {
    private String userName;
    private String password;

    public UsuarioLoginDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UsuarioLoginDTO(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
