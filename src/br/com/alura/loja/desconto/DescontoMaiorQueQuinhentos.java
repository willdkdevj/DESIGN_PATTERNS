package br.com.alura.loja.desconto;

import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public class DescontoMaiorQueQuinhentos extends Desconto {

    public DescontoMaiorQueQuinhentos(Desconto proximoDesconto) {
        super(proximoDesconto);
    }

    @Override
    public BigDecimal validarCalcular(Orcamento orcamento) {
        return orcamento.getValor().multiply(new BigDecimal("0.1"));
    }

    @Override
    public boolean checarRegra(Orcamento orcamento) {
        return orcamento.getValor().compareTo(new BigDecimal("500")) > 0;
    }
}
