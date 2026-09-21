import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше 0");
        }
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while(queue.size() == capacity) {
            wait();
        }
        queue.offer(item);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while(queue.isEmpty()) {
            wait();
        }
        T item = queue.poll();
        notifyAll();
        return item; 
    }

    public synchronized int size() {
        return queue.size(); 
    }



    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new BlockingQueue<>(3);

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    q.enqueue(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("Take: " + q.dequeue());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }

}   
