package org.example.practicaparcial.model;

import java.util.List;

public class Role {

    private long id;
    private String name;
    private List<User> userList;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }


}
