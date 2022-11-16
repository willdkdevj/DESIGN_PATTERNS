package br.com.alura.loja.model;

import br.com.alura.loja.orcamento.situacao.Situacao;

import java.math.BigDecimal;

public class Orcamento {

	private BigDecimal valor;
	private Integer qtdItens;
	private Situacao situacao;

	public Orcamento(BigDecimal valor, Integer qtd) {
		this.valor = valor;
		this.qtdItens = qtd;
	}

	public Orcamento(BigDecimal valor, Integer qtd, Situacao situacao) {
		this.valor = valor;
		this.qtdItens = qtd;
		this.situacao = situacao;
	}
	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQtdItens() {
		return qtdItens;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}
}
