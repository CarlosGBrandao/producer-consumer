import java.util.Random;

public class Consumidor implements Runnable {
    private final BufferCompartilhado buffer;

    public Consumidor(BufferCompartilhado buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {

                int item = buffer.consumir();


                Thread.sleep(2000 + new Random().nextInt(2000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Consumidor foi interrompido.");
        }
    }
}