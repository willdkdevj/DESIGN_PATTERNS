package br.com.alura.loja.orcamento.situacao;

import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public class EmAnalise extends Situacao {

    public BigDecimal calcularValorDescontoExtra(Orcamento orcamento){
        return orcamento.getValor().multiply(new BigDecimal("0.05"));
    }

    public void aprovar(Orcamento orcamento){
        orcamento.setSituacao(new Aprovado());
    }

    @Override
    public void reprovar(Orcamento orcamento) {
        orcamento.setSituacao(new Reprovado());
    }
}
