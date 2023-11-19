package com.example.b07project.User;

public abstract class User {
    private String name;
    private String email;
    private String password;
    private String userId;
    private String role;

    protected User(String name,String email, String password, String userId, String role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.role = role;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getUserId(){
        return userId;
    }
    public String getRole(){
        return role;
    }

}
