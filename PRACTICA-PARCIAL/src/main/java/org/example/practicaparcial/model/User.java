package org.example.practicaparcial.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private long id;
    private String name;
    private String username;
    private String password;

    private List<Role> roleList;

    public User() {}

    public User(long id, String name, String username, String password, List<Role> roleList) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Role> getRoleList() {
        if (roleList == null) {
            roleList = new ArrayList<>();
        }
        return roleList;
    }
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
