package com.rathana.local_storage.model;

public class User {

    private String name;
    private String password;
    private boolean isLogin;
    public User(String name, String password,boolean isLogin) {
        this.name = name;
        this.password = password;
        this.isLogin=isLogin;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
