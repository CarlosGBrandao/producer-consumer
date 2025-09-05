import java.util.LinkedList;
import java.util.Queue;

public class BufferCompartilhado {
    private final Queue<Integer> buffer;
    private final int tamanhoMaximo;


    public BufferCompartilhado(int tamanhoMaximo) {
        this.buffer = new LinkedList<>();
        this.tamanhoMaximo = tamanhoMaximo;
        System.out.println("Buffer criado com tamanho T = " + tamanhoMaximo);
    }


    public synchronized void produzir(int item) throws InterruptedException {

        while (buffer.size() == tamanhoMaximo) {
            System.out.println("Buffer CHEIO. Produtor " + Thread.currentThread().getName() + " esperando...");
            wait();
        }


        buffer.add(item);
        System.out.printf("Produtor %s produziu o item %d (Tamanho do buffer: %d)\n",
                Thread.currentThread().getName(), item, buffer.size());


        notifyAll();
    }

    // Método para um consumidor remover um item do buffer
    public synchronized int consumir() throws InterruptedException {

        while (buffer.isEmpty()) {
            System.out.println("Buffer VAZIO. Consumidor " + Thread.currentThread().getName() + " esperando...");
            wait(); // Libera o lock e coloca a thread em estado de espera.
        }


        int item = buffer.poll(); // poll() remove o primeiro elemento da fila
        System.out.printf("Consumidor %s consumiu o item %d (Tamanho do buffer: %d)\n",
                Thread.currentThread().getName(), item, buffer.size());


        notifyAll();

        return item;
    }
}