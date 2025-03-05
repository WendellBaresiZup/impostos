package br.com.zup.impostos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

@Entity
public class Usuario {
    @Id
    @UuidGenerator
    private String uuid;
    private String userName;
    private String password;
    private String role;

    public Usuario() {
    }

    public Usuario(String uuid, String userName, String password, String role) {
        this.uuid = uuid;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
