public class Sistema {
    public static void main(String[] args) {
        if (args.length != 3) {

            System.exit(1);
        }

        // Leitura dos parâmetros configuráveis via linha de comando
        int numProdutores = Integer.parseInt(args[0]); // P
        int numConsumidores = Integer.parseInt(args[1]); // C
        int tamBuffer = Integer.parseInt(args[2]); // T

        System.out.printf("Iniciando sistema com:\n - Produtores (P): %d\n - Consumidores (C): %d\n - Tamanho do Buffer (T): %d\n\n",
                numProdutores, numConsumidores, tamBuffer);


        // Criação do buffer
        BufferCompartilhado buffer = new BufferCompartilhado(tamBuffer);

        // Criação e inicialização das threads produtoras
        for (int i = 0; i < numProdutores; i++) {
            Thread produtorThread = new Thread(new Produtor(buffer), "P" + (i + 1));
            produtorThread.start();
        }

        // Criação e inicialização das threads consumidoras
        for (int i = 0; i < numConsumidores; i++) {
            Thread consumidorThread = new Thread(new Consumidor(buffer), "C" + (i + 1));
            consumidorThread.start();
        }
    }
}