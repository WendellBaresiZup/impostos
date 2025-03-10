package br.com.zup.impostos.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
public class Usuario {
    @Id
    @UuidGenerator
    private String uuid;
    @NotBlank(message = "Nome é obrigatório")
    private String userName;
    @NotBlank(message = "Senha é obrigatória")
    private String password;

    public Usuario() {
    }

    public Usuario(String uuid, String userName, String password) {
        this.uuid = uuid;
        this.userName = userName;
        this.password = password;
    }
}
