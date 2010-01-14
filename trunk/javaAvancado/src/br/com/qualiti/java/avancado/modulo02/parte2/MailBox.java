package br.com.qualiti.java.avancado.modulo02.parte2;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class MailBox {

    private String mensagem;
    private boolean temMensagem = false;

    public synchronized String get(int id) {
        String retorno = "";
        while (temMensagem == false) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }

        temMensagem = false;
        retorno = mensagem;
        mensagem = "";
        System.out.println("Consumer #" + id  + " got: " + retorno);
        notifyAll();
        return retorno;
    }

    public synchronized void put(String value, int id) {

        while (temMensagem == true) {
            try {
                wait();
            } catch (InterruptedException e) { }
        }
        mensagem = value;
        temMensagem = true;
        System.out.println("Producer #" + id
                               + " put: " + mensagem);
        notifyAll();
    }
}
