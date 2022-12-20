package br.com.alura.loja;

import br.com.alura.loja.imposto.ISS;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.CalculadoraDeImpostos;

import java.math.BigDecimal;

public class TestesImpostos {

    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(new BigDecimal("500"), 1);
        CalculadoraDeImpostos calculadoraDeImpostos = new CalculadoraDeImpostos();
        System.out.println(calculadoraDeImpostos.calcular(orcamento, new ISS()));
    }
}
