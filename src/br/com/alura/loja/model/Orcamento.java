package br.com.alura.loja.model;

import br.com.alura.loja.orcamento.interfaces.Orcavel;
import br.com.alura.loja.orcamento.situacao.Finalizado;
import br.com.alura.loja.orcamento.situacao.Situacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Orcamento implements Orcavel {

	private BigDecimal valor;
	private List<Orcavel> itens;
	private Situacao situacao;

	public Orcamento(){

	}
	public Orcamento(BigDecimal valor, List<Orcavel> qtd) {
		this.valor = valor;
		this.itens = qtd;
	}

	public Orcamento(BigDecimal valor, List<Orcavel> qtd, Situacao situacao) {
		this.valor = valor;
		this.itens = qtd;
		this.situacao = situacao;
	}
	public BigDecimal getValor() {
		valor = BigDecimal.ZERO;
		if(!itens.isEmpty()){
			for (Orcavel o : itens){
				Item i = (Item) o;
				valor = valor.add(i.getValor());
			}
		}
		return valor;
	}

	public List<Orcavel> getItens() {
		if(itens == null || itens.isEmpty()){
			itens = new ArrayList<>();
		}
		return itens;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public Boolean isFinalizado(){
		return this.situacao instanceof Finalizado;
	}

	public Integer getQtdItens() {
		return itens.size();
	}
	public void addItem(Orcavel item){
		getItens().add(item);
	}
}
