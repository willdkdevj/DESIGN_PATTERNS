package br.com.alura.loja.model;

import br.com.alura.loja.orcamento.interfaces.Orcavel;

import java.math.BigDecimal;

public class Item implements Orcavel {

    private String nome;

    private BigDecimal valor;

    public Item() {
    }

    public Item(String nome, BigDecimal valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
