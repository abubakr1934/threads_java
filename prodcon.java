import java.util.ArrayList;

public class ProducerConsumerWithThreads {
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> queue = new ArrayList<>();
        int size = 5;
        Consumer c = new Consumer(queue, size);
        Thread producerThread = new Producer(queue, size);
        Thread consumerThread = new Thread(c, "consumer");
        producerThread.start();
        Thread.sleep(10); // Sleep for a short time to allow producerThread to start
        consumerThread.start();
    }
}

class Producer extends Thread {
    ArrayList<Integer> queue;
    final int size;

    public Producer(ArrayList<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        for (int i = 0; i < 7; i++) {
            synchronized (queue) {
                while (queue.size() == size) {
                    System.out.println("Queue is full, producer is waiting");
                    try {
                        queue.wait();
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted");
                    }
                }
                System.out.println("Produced " + i);
                queue.add(i);
                queue.notifyAll();
            }
        }
    }
}

class Consumer implements Runnable {
    ArrayList<Integer> queue;
    final int size;

    public Consumer(ArrayList<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException ex) {
                        System.out.println("Interrupted");
                    }
                }
                int k = queue.remove(0);
                System.out.println("Consumed " + k);
                queue.notifyAll();
            }
        }
    }
}
