package com.example.smile.fristapplication.bean;

import java.io.Serializable;

/**
 * Created by Smile on 2016/12/7.
 */

public class User implements Serializable {
    String Username;
    String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public User(String username, String password) {
        Username = username;
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
