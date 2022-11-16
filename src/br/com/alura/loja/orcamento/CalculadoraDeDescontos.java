package br.com.alura.loja.orcamento;

import br.com.alura.loja.desconto.DescontoMaiorQueCincoItens;
import br.com.alura.loja.desconto.DescontoMaiorQueQuinhentos;
import br.com.alura.loja.desconto.SemDesconto;
import br.com.alura.loja.desconto.Desconto;
import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public class CalculadoraDeDescontos {

    public BigDecimal calcular(Orcamento orcamento){
        Desconto desconto= new DescontoMaiorQueCincoItens(
                new DescontoMaiorQueQuinhentos(
                        new SemDesconto()));

        return desconto.calcular(orcamento);
    }
}
