package br.com.alura.loja;

import br.com.alura.loja.model.Item;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.CalculadoraDeDescontos;

import java.math.BigDecimal;

public class TestesDescontos {

    public static void main(String[] args) {
        Orcamento orcamento1 = new Orcamento();
        orcamento1.addItem(new Item("Tenis", new BigDecimal("200")));
        orcamento1.addItem(new Item("Tenis2", new BigDecimal("200")));
        orcamento1.addItem(new Item("Tenis3", new BigDecimal("200")));
        orcamento1.addItem(new Item("Tenis4", new BigDecimal("200")));
        orcamento1.addItem(new Item("Tenis5", new BigDecimal("200")));
        Orcamento orcamento2 = new Orcamento();
        orcamento2.addItem(new Item("Bermuda", new BigDecimal("100")));
        CalculadoraDeDescontos calculadoraDeDescontos = new CalculadoraDeDescontos();
        System.out.println(calculadoraDeDescontos.calcular(orcamento1));
        System.out.println(calculadoraDeDescontos.calcular(orcamento2));
    }
}
