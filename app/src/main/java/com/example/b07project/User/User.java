package com.example.b07project.User;

public abstract class User {
    private String name;
    private String email;
    private String password;
    private boolean isAdmin;

    protected User(String name,String email, String password, boolean isAdmin){
        this.name = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
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
    public boolean checkAdmin(){
        return isAdmin;
    }

}
