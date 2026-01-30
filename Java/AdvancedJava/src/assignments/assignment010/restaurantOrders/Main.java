package assignments.assignment010.restaurantOrders;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Order implements Runnable {
    private final int id;

    public Order(int id) {
        this.id = id;
    }

    public void run() {
        String chef = Thread.currentThread().getName();
        System.out.println("Chef " + chef + " preparing Order " + id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        System.out.println("Order " + id + " Ready.");
    }
}

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            executor.execute(new Order(i));
        }

        executor.shutdown();
    }
}

