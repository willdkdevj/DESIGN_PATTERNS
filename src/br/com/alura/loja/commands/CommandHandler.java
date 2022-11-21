package br.com.alura.loja.commands;

import br.com.alura.loja.enums.CommandType;
import br.com.alura.loja.model.Pedido;

public interface CommandHandler {

    void executar(Pedido pedido, CommandType type);
}
