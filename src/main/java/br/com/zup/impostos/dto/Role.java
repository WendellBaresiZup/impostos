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
}
