package com.example.lavacarapp.model;

public class UserInfo {
    private long id;
    private String nome;
    private String telefone;
    private String email;
    private String password;

    public UserInfo() {

    }

    public UserInfo(long id, String nome, String telefone, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.password = password;
    }

    public UserInfo(String nome, String telefone, String email, String password) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

