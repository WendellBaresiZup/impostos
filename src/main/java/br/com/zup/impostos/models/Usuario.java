package br.com.zup.impostos.models;

import br.com.zup.impostos.dto.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

@Entity(name = "USUARIO")
@Data
public class Usuario {
    @Id
    @UuidGenerator
    private String uuid;
    @NotBlank(message = "Nome é obrigatório")
    private String userName;
    @NotBlank(message = "Senha é obrigatória")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario() {
    }

    public Usuario(String uuid, String userName, String password, Role role) {
        this.uuid = uuid;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
