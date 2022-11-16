package br.com.alura.loja.orcamento.situacao;

import br.com.alura.loja.exceptions.ErrorException;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.situacao.Aprovado;
import br.com.alura.loja.orcamento.situacao.Reprovado;

import java.math.BigDecimal;

public abstract class Situacao {

    public BigDecimal calcularValorDescontoExtra(Orcamento orcamento){
        return BigDecimal.ZERO;
    }

    public void aprovar(Orcamento orcamento){
        throw new ErrorException("Orcamento nao pode ser aprovado!");
    }

    public void reprovar(Orcamento orcamento){
        throw new ErrorException("Orcamento nao pode ser reprovado!");
    }

    public void finalizar(Orcamento orcamento){
        throw new ErrorException("Orcamento nao pode ser finalizado!");
    }

}
