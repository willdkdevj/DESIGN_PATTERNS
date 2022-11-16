package br.com.alura.loja.desconto;

import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public class SemDesconto extends Desconto {
    public SemDesconto() {
        super(null);
    }

    @Override
    public BigDecimal validarCalcular(Orcamento orcamento) {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean checarRegra(Orcamento orcamento) {
        return Boolean.TRUE;
    }
}
