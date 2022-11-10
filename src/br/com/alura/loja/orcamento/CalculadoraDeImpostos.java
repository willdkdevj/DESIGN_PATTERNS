package br.com.alura.loja.orcamento;

import java.math.BigDecimal;

import br.com.alura.loja.imposto.Imposto;
import br.com.alura.loja.model.Orcamento;

public class CalculadoraDeImpostos {

	public BigDecimal calcular(Orcamento orcamento, Imposto imposto) {

		return imposto.calcular(orcamento);
	}

}
