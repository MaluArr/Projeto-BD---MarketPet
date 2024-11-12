package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Usuario {
    private Long cpf;
    private String nomeReal;
    private String nomeUsuario;
    private String email;
    private String senha;
    private Integer telefone1;
    private Integer telefone2;

    // Construtores
    public Usuario() {
    }

    public Usuario(Long cpf, String nomeReal, String nomeUsuario, String email, String senha, Integer telefone1, Integer telefone2) {
        this.cpf = cpf;
        this.nomeReal = nomeReal;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    // Getters e Setters
    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNomeReal() {
        return nomeReal;
    }

    public void setNomeReal(String nomeReal) {
        this.nomeReal = nomeReal;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(Integer telefone1) {
        this.telefone1 = telefone1;
    }

    public Integer getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(Integer telefone2) {
        this.telefone2 = telefone2;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(cpf, usuario.cpf) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(nomeUsuario, usuario.nomeUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, email, nomeUsuario);
    }

    // Método toString
    @Override
    public String toString() {
        return "Usuario{" +
                "cpf=" + cpf +
                ", nomeReal='" + nomeReal + '\'' +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", email='" + email + '\'' +
                ", telefone1=" + telefone1 +
                ", telefone2=" + telefone2 +
                '}';
    }
}
