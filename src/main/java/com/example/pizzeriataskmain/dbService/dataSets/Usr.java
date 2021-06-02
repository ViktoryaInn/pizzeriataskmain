package com.example.pizzeriataskmain.dbService.dataSets;

public class Usr {

    private String login;
    private String password;
    private String role;

    public Usr(String login, String password, String role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() { return login; }

    public String getPassword() { return password; }

    public String getRole() { return role; }

    public void setLogin(String login) { this.login = login; }

    public void setPassword(String password) { this.password = password; }

    public void setRole(String role) { this.role = role; }
}
