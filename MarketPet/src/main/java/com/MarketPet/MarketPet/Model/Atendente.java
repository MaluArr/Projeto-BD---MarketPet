package com.MarketPet.MarketPet.Model;

import java.util.Objects;

public class Atendente {
    private Integer idAtendente;
    private Long cpfFuncionario;

    // Construtores
    public Atendente() {}

    public Atendente(Integer idAtendente, Long cpfFuncionario) {
        this.idAtendente = idAtendente;
        this.cpfFuncionario = cpfFuncionario;
    }

    // Getters e Setters
    public Integer getIdAtendente() {
        return idAtendente;
    }

    public void setIdAtendente(Integer idAtendente) {
        this.idAtendente = idAtendente;
    }

    public Long getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(Long cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendente atendente = (Atendente) o;
        return Objects.equals(idAtendente, atendente.idAtendente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendente);
    }

    // Método toString
    @Override
    public String toString() {
        return "Atendente{" +
                "idAtendente=" + idAtendente +
                ", cpfFuncionario=" + cpfFuncionario +
                '}';
    }
}