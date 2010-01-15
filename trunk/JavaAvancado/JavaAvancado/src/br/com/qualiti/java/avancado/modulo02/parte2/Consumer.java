package br.com.qualiti.java.avancado.modulo02.parte2;
public class Consumer extends Thread {
    private MailBox mailbox;
    private int number;

    public Consumer(MailBox c, int number) {
        mailbox = c;
        this.number = number;
    }

    @Override
	public void run() {
        for (int i = 0; i < 10; i++) {
            mailbox.get(this.number);
        }
    }
}

