package br.com.zup.impostos.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {
    private Role role;

    @Test
    public void RoleTeste(){
        assertEquals("ROLE_ADMIN", Role.ROLE_ADMIN.getRoleName());
        assertEquals("ROLE_USER", Role.ROLE_USER.getRoleName());
    }
}
