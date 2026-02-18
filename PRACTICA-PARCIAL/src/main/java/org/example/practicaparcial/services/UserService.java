package org.example.practicaparcial.services;

import org.example.practicaparcial.model.Role;
import org.example.practicaparcial.model.User;
import org.example.practicaparcial.repositories.RoleRepository;
import org.example.practicaparcial.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void addUser(String name, String username, String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUsername(username);
        userRepository.save(user);
    }

    public void addRole(long idUser, long idRole) {
        User user = userRepository.findById(idUser);
        Role role = roleRepository.findById(idRole);

        user.getRoleList().add(role);
        role.getUserList().add(user);
    }

    public List<User> getUsers() {
        return Arrays.asList(new User(1,"test", "testUser", "password", null));
    }

    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
