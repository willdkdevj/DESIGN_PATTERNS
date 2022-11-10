package br.com.alura.loja.model;

import java.math.BigDecimal;

public class Orcamento {

	private BigDecimal valor;
	private Integer qtdItens;
	public Orcamento(BigDecimal valor, Integer qtd) {
		this.valor = valor;
		this.qtdItens = qtd;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQtdItens() {
		return qtdItens;
	}
}
