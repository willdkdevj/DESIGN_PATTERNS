package br.com.alura.loja.commands;

import br.com.alura.loja.enums.CommandType;
import br.com.alura.loja.model.Pedido;

public class EnviarEmail implements CommandHandler{
    @Override
    public void executar(Pedido pedido, CommandType type) {
        if (type.equals(CommandType.EMAIL)){
            executarEnvio(pedido);
        }
    }

    private void executarEnvio(Pedido pedido){
        System.out.println("Enviar email com os dados do pedido: " + pedido.toString());
    }
}
