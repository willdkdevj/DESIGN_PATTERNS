package br.com.alura.loja;

import br.com.alura.loja.imposto.ISS;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.CalculadoraDeImpostos;
import br.com.alura.loja.orcamento.ValidadoraDeDescontosExtras;
import br.com.alura.loja.orcamento.situacao.EmAnalise;

import java.math.BigDecimal;

public class TestesDescontoExtra {

    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(new BigDecimal("500"), 1);
        ValidadoraDeDescontosExtras validadoraDeDescontosExtras = new ValidadoraDeDescontosExtras();
        System.out.println(validadoraDeDescontosExtras.aplicarDescontoExtra(orcamento, new EmAnalise()));
    }
}
