package br.com.zup.impostos.dto;

import br.com.zup.impostos.exceptions.RoleInvalidException;

public enum Role {
    ROLE_ADMIN,
    ROLE_USER;

    public String getRoleName(){
        return this.name();
    }

    public static Role fromRoleName(String roleName){
        for (Role role : Role.values()) {
            if (role.name().equals(roleName)){
                return role;
            }
        }
        throw new RoleInvalidException("Nome de role inválido: " + roleName);
    }
}
