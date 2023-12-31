package com.example.demo.model;

public class Account {
    // data of the main user
    String username ;
    String password ;
    Long id ;
    public String getUsername() {
        return username;
    }
    public Account(Long id , String username , String password){
        this.id = id ;
        this.username = username ;
        this.password = password ;
    }
    public Long getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "id : " + id + "\n" + "user name : " + username + "\n" ;
    }
}
