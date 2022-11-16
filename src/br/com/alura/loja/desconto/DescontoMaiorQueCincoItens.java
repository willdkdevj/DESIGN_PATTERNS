package br.com.alura.loja.desconto;

import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public class DescontoMaiorQueCincoItens extends Desconto {
    public DescontoMaiorQueCincoItens(Desconto proximoDesconto) {
        super(proximoDesconto);
    }

    @Override
    public BigDecimal validarCalcular(Orcamento orcamento) {
            return orcamento.getValor().multiply(new BigDecimal("0.06"));
    }

    @Override
    public boolean checarRegra(Orcamento orcamento) {
        return orcamento.getQtdItens() > 5;
    }
}
