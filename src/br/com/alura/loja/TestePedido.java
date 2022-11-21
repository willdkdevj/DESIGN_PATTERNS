package br.com.alura.loja;

import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.model.Pedido;
import br.com.alura.loja.orcamento.CriadorDePedido;

import java.math.BigDecimal;
import java.time.LocalDate;

import static br.com.alura.loja.orcamento.CriadorDePedido.gerarPedido;

public class TestePedido {

    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(new BigDecimal("600"), 2);
        Cliente cliente = new Cliente("Jose", "Sobrano", LocalDate.of(2000, 1, 1));

        Pedido pedido = gerarPedido(cliente, orcamento);

    }
}
