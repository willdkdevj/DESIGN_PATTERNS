package br.com.alura.loja.orcamento;

import br.com.alura.loja.commands.CommandHandler;
import br.com.alura.loja.enums.CommandType;
import br.com.alura.loja.model.Cliente;
import br.com.alura.loja.model.Orcamento;
import br.com.alura.loja.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public class CriadorDePedido {

    private List<CommandHandler> commandList;

    public CriadorDePedido(List<CommandHandler> list){
        this.commandList = list;
    }

    public void gerarPedido(Cliente cliente, Orcamento orcamento, CommandType type){
        Pedido pedido = new Pedido(cliente, LocalDateTime.now(), orcamento);
        commandList.forEach(command -> {
            command.executar(pedido, type);
        });

    }
}
