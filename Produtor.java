import java.util.Random;

public class Produtor implements Runnable {
    private final BufferCompartilhado buffer;
    private int itemCounter = 0;

    public Produtor(BufferCompartilhado buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int item = itemCounter++;
                buffer.produzir(item);


                Thread.sleep(1000 + new Random().nextInt(1500));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Produtor foi interrompido.");
        }
    }
}