package br.com.alura.loja.orcamento;

import br.com.alura.loja.commands.CRUD;
import br.com.alura.loja.commands.CommandHandler;
import br.com.alura.loja.commands.EnviarEmail;
import br.com.alura.loja.enums.CommandType;
import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.model.Pedido;

import java.time.LocalDateTime;

public class CriadorDePedido {

    public static Pedido gerarPedido(Cliente cliente, Orcamento orcamento){
        Pedido pedido = new Pedido(cliente, LocalDateTime.now(), orcamento);
        executarEnvio(pedido);
        executarCriacaoNoBD(pedido);
        return pedido;
    }

    private static void executarEnvio(Pedido pedido){
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.executar(pedido, CommandType.EMAIL);
    }
    private static void executarCriacaoNoBD(Pedido pedido){
        CRUD crud = new CRUD();
        crud.executar(pedido, CommandType.CREATED);
    }
}
