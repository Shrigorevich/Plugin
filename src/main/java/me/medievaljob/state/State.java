package me.medievaljob.state;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class State {
    private List<User> users;

    public State(List<User> users){
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public User getUser(String name){
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }
}
