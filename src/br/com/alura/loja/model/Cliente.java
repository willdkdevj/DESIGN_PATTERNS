package br.com.alura.loja.model;

import java.time.LocalDate;

public class Cliente {

    private String Nome;
    private String sobrenome;
    private LocalDate dtNasc;

    public Cliente(String nome, String sobrenome, LocalDate dtNasc) {
        Nome = nome;
        this.sobrenome = sobrenome;
        this.dtNasc = dtNasc;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }
}
