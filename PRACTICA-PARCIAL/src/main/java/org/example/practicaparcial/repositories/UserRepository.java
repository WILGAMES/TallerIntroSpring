package org.example.practicaparcial.repositories;

import jakarta.annotation.PostConstruct;
import org.example.practicaparcial.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository {

    private long currentUserId = 0;

    @PostConstruct
    public void init(){
        User user = new User(0,"name", "username", "password", null);
        save(user);
    }

    private List<User> userList = new ArrayList<>();
    public void save(User user) {
        currentUserId++;
        user.setId(currentUserId);
        userList.add(user);
    }

    public User findById(long id) {
        return userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    public List<User> findAll() {
        return userList;
    }
}
