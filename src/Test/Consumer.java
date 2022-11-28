package Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private int id;
    private BlockingQueue<Item> queue;
    Random random = new Random();

    public Consumer(int id, BlockingQueue<Item> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        Item item;
        try {
            while ((item = queue.take()).getId() != -1) {
                System.out.printf("Consumer %d consumed %d%n",
                        id, item.getId());

                Thread.sleep(30);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
