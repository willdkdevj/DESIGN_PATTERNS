package br.com.alura.loja.imposto;

import br.com.alura.loja.model.Orcamento;

import java.math.BigDecimal;

public abstract class Imposto {

	private Imposto outro;

	public Imposto(Imposto outro) {
		this.outro = outro;
	}

	protected abstract BigDecimal realizarCalculo(Orcamento orcamento);
	public BigDecimal calcular(Orcamento orcamento){
		BigDecimal valorOutroImposto = BigDecimal.ZERO;
		BigDecimal valorImposto = realizarCalculo(orcamento);

		if(this.outro != null){
			valorOutroImposto = outro.realizarCalculo(orcamento);
		}
		return valorImposto.add(valorOutroImposto);
	}

}
