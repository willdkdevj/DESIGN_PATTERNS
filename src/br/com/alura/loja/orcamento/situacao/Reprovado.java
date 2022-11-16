package br.com.alura.loja.orcamento.situacao;

import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public class Reprovado extends Situacao{

    @Override
    public void reprovar(Orcamento orcamento) {
        orcamento.setSituacao(new Reprovado());
    }
}
