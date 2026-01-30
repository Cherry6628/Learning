package classes;

public class DeadLock {
	public static void main(String[] args) {
        final Object resource1 = new Object();
        final Object resource2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked resource 1");

                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 1: Trying to lock resource 2...");
                synchronized (resource2) { 
                    System.out.println("Thread 1: Locked resource 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked resource 2");

//                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Thread 2: Trying to lock resource 1...");
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked resource 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
