package br.com.alura.loja;

import br.com.alura.loja.commands.CRUD;
import br.com.alura.loja.commands.EnviarEmail;
import br.com.alura.loja.enums.CommandType;
import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.orcamento.CriadorDePedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

public class TestePedido {

    public static void main(String[] args) {
        Orcamento orcamento = new Orcamento(new BigDecimal("600"), 2);
        Cliente cliente = new Cliente("Jose", "Sobrano", LocalDate.of(2000, 1, 1));

        CriadorDePedido criadorDePedido = new CriadorDePedido(Arrays.asList(new CRUD(),
                                                              new EnviarEmail()));

        criadorDePedido.gerarPedido(cliente, orcamento, CommandType.CREATED);

    }
}
