class callme {
    synchronized void call(String s) {
        try {
            System.out.print("[" + s);
            Thread.sleep(1000);
            System.out.println("]");
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
    }
}

class caller extends Thread {
    String s;
    callme ob;

    caller(callme ob, String s) {
        this.ob = ob;
        this.s = s;
    }

    @Override
    public void run() {
        synchronized (ob) {
            ob.call(s);
        }
    }
}

public class threadsync {
    public static void main(String a[]) {
        callme c = new callme();
        caller t1 = new caller(c, "hello");
        caller t2 = new caller(c, "world");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
