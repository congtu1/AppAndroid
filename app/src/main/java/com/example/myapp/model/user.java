package com.example.myapp.model;

public class user {
    private String name;
    private String email;
    private String password;
    public user(String email, String name , String password) {
        super();
        this.email=email;
        this.name=name;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
