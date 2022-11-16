package br.com.alura.loja.orcamento;

import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.situacao.Situacao;

import java.math.BigDecimal;

public class ValidadoraDeDescontosExtras {

    public BigDecimal aplicarDescontoExtra(Orcamento orcamento, Situacao situacao){
        return situacao.calcularValorDescontoExtra(orcamento);
    }
}
