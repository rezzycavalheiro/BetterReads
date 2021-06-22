package com.example.betterreads.model;

public class UserInfo {
    private long id;
    private String nome;
    private String email;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String nome) {
        this.nome = nome;
    }

    public UserInfo(String nome, String email, String password) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

