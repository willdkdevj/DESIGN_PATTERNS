package br.com.alura.loja.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class Pedido {

    private static BigInteger id;
    private Cliente cliente;
    private LocalDateTime data;
    private  Orcamento orcamento;

    public Pedido(Cliente cliente, LocalDateTime data, Orcamento orcamento) {
        this.cliente = cliente;
        this.data = data;
        this.orcamento = orcamento;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente=" + cliente +
                ", data=" + data +
                ", orcamento=" + orcamento +
                '}';
    }
}
