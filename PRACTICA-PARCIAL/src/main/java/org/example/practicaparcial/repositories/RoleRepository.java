package org.example.practicaparcial.repositories;

import org.example.practicaparcial.model.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class RoleRepository {

    private long currentRoleId = 0;
    private List<Role> rolesList = new ArrayList<>();

    public void save(Role role) {
        currentRoleId++;
        role.setId(currentRoleId);
        rolesList.add(role);
    }

    public Role findById(long id) {
        return rolesList.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }
}

