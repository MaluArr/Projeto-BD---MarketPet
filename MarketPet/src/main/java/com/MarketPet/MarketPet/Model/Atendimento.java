package com.MarketPet.MarketPet.Model;

import java.time.LocalDate;
import java.util.Objects;

public class Atendimento {
    private Integer idAtendimento;
    private Long cpfFuncionario;
    private Long cpfUsuario;
    private Integer idChat;
    private LocalDate dataAtendimento;
    private String categoria;

    // Construtores
    public Atendimento() {}

    public Atendimento(Integer idAtendimento, Long cpfFuncionario, Long cpfUsuario,
                       Integer idChat, LocalDate dataAtendimento, String categoria) {
        this.idAtendimento = idAtendimento;
        this.cpfFuncionario = cpfFuncionario;
        this.cpfUsuario = cpfUsuario;
        this.idChat = idChat;
        this.dataAtendimento = dataAtendimento;
        this.categoria = categoria;
    }

    // Getters e Setters
    public Integer getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(Integer idAtendimento) {
        this.idAtendimento = idAtendimento;
    }

    public Long getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(Long cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public Long getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(Long cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    public LocalDate getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(LocalDate dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Métodos equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendimento that = (Atendimento) o;
        return Objects.equals(idAtendimento, that.idAtendimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAtendimento);
    }

    // Método toString
    @Override
    public String toString() {
        return "Atendimento{" +
                "idAtendimento=" + idAtendimento +
                ", cpfFuncionario=" + cpfFuncionario +
                ", cpfUsuario=" + cpfUsuario +
                ", idChat=" + idChat +
                ", dataAtendimento=" + dataAtendimento +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}