package br.com.qualiti.java.avancado.modulo02.parte2;
public class Producer extends Thread {
    private MailBox mailBox;
    private int number;

    public Producer(MailBox m, int number) {
        mailBox = m;
        this.number = number;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            String mensagem = "mensagem "+i+" do produtor "+this.number;
            mailBox.put(mensagem, this.number);
            try {
                sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) { }
        }
    }
}
