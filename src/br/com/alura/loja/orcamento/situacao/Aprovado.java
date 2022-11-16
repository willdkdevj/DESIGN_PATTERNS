package br.com.alura.loja.orcamento.situacao;

import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public class Aprovado extends Situacao {

    public BigDecimal calcularValorDescontoExtra(Orcamento orcamento){
        return orcamento.getValor().multiply(new BigDecimal("0.02"));
    }

    @Override
    public void finalizar(Orcamento orcamento) {
        orcamento.setSituacao(new Finalizado());
    }
}
