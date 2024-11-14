package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Funcionario {
    private Long cpfFuncionario;
    private String nome;

    // Construtores
    public Funcionario() {}

    public Funcionario(Long cpfFuncionario, String nome) {
        this.cpfFuncionario = cpfFuncionario;
        this.nome = nome;
    }

    // Validações personalizadas
    public boolean isNomeValido() {
        return nome != null && !nome.trim().isEmpty() && nome.length() <= 255;
    }

    // Getters e Setters
    public Long getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(Long cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return Objects.equals(cpfFuncionario, that.cpfFuncionario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpfFuncionario);
    }

    // Método toString
    @Override
    public String toString() {
        return "Funcionario{" +
                "cpfFuncionario=" + cpfFuncionario +
                ", nome='" + nome + '\'' +
                '}';
    }
}