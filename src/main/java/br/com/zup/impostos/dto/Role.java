package br.com.zup.impostos.dto;

public enum Role {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName(){
        return this.name();
    }

    public static Role fromRoleName(String roleName){
        for (Role role : Role.values()) {
            if (role.roleName.equals(roleName)){
                return role;
            }
        }
        throw new IllegalArgumentException("Nome de role inválido: " + roleName);
    }
}
