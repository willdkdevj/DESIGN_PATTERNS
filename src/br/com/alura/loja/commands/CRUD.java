package br.com.alura.loja.commands;

import br.com.alura.loja.enums.CommandType;
import br.com.alura.loja.exceptions.ErrorException;
import br.com.alura.loja.model.Pedido;

public class CRUD implements CommandHandler {

    public void executar(Pedido pedido, CommandType type){
        switch (type){
            case CREATED:
                executeSave(pedido);
                break;
            case READ:
                executeRead(pedido);
                break;
            case UPDATED:
                executeUpdate(pedido);
                break;
            case DELETE:
                executeDelete(pedido);
                break;
            default:
                throw new ErrorException("Não foi informado o comando correto!");
        }
    }

    private void executeSave(Pedido pedido){
        System.out.println("Salvar pedido");
    }

    private void executeRead(Pedido pedido){
        System.out.println("Consultar pedido");
    }

    private void executeUpdate(Pedido pedido){
        System.out.println("Atualizar pedido");
    }

    private void executeDelete(Pedido pedido){
        System.out.println("Deletar pedido");
    }
}
