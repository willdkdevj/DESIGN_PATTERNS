package br.com.alura.loja;

import br.com.alura.loja.imposto.ICMS;
import br.com.alura.loja.imposto.ISS;
import br.com.alura.loja.model.Item;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.CalculadoraDeImpostos;

import java.math.BigDecimal;

public class TestesImpostos {

    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento();
        orcamento.addItem(new Item("Calça", new BigDecimal("100")));
        CalculadoraDeImpostos calculadoraDeImpostos = new CalculadoraDeImpostos();
        System.out.println(calculadoraDeImpostos.calcular(orcamento, new ISS(new ICMS(null))));
    }
}
