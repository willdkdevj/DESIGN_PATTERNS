package br.com.alura.loja.model;

import java.math.BigDecimal;

public abstract class Desconto {
        public Desconto proximoDesconto;

    public Desconto(Desconto desconto){
        this.proximoDesconto = desconto;
    }
    public BigDecimal calcular(Orcamento orcamento){
        if(checarRegra(orcamento)){
            return validarCalcular(orcamento);
        }
        return proximoDesconto.calcular(orcamento);
    }

    protected abstract BigDecimal validarCalcular(Orcamento orcamento);
    public abstract boolean checarRegra(Orcamento orcamento);
}
