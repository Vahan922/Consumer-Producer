package Test;

import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.util.Random;
import java.util.concurrent.*;


public class Main {

    public static void main(String[] args) {
        BlockingQueue<Item> queue = new LinkedBlockingQueue<>();
        Random random=new Random();
        ExecutorService service = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < random.nextInt(8); i++) {
            Producer p1 = new Producer(1, queue);
            service.execute(p1);

        }
        for (int i = 0; i < random.nextInt(8); i++) {

            Consumer c1 = new Consumer(1, queue);
            service.execute(c1);
        }
        service.shutdown();
    }
}
