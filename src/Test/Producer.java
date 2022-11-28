package Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private int id;
    private BlockingQueue<Item> queue;
    Random random = new Random();

    public Producer(int id, BlockingQueue<Item> queue) {
        this.id = id;
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            Item item = new Item(i);
            try {
                System.out.printf("Producer %d produced %d%n",
                        id, item.getId());
                queue.put(item);
                Thread.sleep(20);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {

            while(queue.remainingCapacity() <25) {
                queue.put(new Item(-1));
            }
            while(queue.remainingCapacity() >25) {
//                System.out.println("Producer again"
//                        );
//                queue.put(new Item(random.nextInt(10)));
//                Thread.sleep(100);
                run();
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
