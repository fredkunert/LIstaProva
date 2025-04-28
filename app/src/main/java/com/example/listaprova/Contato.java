package com.example.listaprova;

import java.io.Serializable;

public class Contato implements Serializable { // A classe implementa Serializable para poder ser passada entre Activities

    // Atributos do contato
    private String nome;
    private String telefone;
    private String email;
    private String linkedin;
    private boolean favorito; // Campo extra que indica se o contato é favorito

    // Construtor que recebe todos os dados do contato
    public Contato(String nome, String telefone, String email, String linkedin, boolean favorito) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.linkedin = linkedin;
        this.favorito = favorito;
    }

    // Métodos getter e setter para acessar e modificar os dados do contato

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

    public String getLinkedIn() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }
}

