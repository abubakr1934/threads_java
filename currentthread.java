class NewThread extends Thread {
    public NewThread(String name) {
        super(name);
        System.out.println("Executing child thread...");
        start();
    }

    @Override
    public void run() {
        for (int i = 5; i > 0; i--) {
            try {
                System.out.println(getName() + " " + i);
                sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("Thread interrupted");
            }
        }
    }
}

public class ThreadDemo2 {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        mainThread.setName("Main Thread");
        System.out.println("Executing main thread");

        NewThread t1 = new NewThread("Thread1");

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread: " + i);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("Main thread exiting.");
    }
}
