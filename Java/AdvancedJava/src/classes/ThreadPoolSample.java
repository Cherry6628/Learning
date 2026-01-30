package classes;

import java.util.concurrent.*;

/* ============================================================
   ✅ PUBLIC CLASS (ENTRY POINT CONTROLLER)
   ============================================================ */
public class ThreadPoolSample {

    public static void main(String[] args) {

        // 👉 Uncomment ONLY ONE at a time

        SingleThreadPoolDemo.main(null);
        // FixedThreadPoolDemo.main(null);
        // CachedThreadPoolDemo.main(null);
        // ScheduledThreadPoolDemo.main(null);
        // WorkStealingPoolDemo.main(null);
        // CallableDemo.main(null);
    }

    /* ============================================================
       🔐 COMMON SHUTDOWN + AWAIT TERMINATION
       ============================================================ */
    static void shutdownAndAwait(ExecutorService executor) {

        // STEP 1: Stop accepting new tasks
        executor.shutdown();

        try {
            // STEP 2: Wait for existing tasks to finish
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {

                // STEP 3: Timeout reached → force shutdown
                System.out.println("Timeout reached. Forcing shutdown...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {

            // STEP 4: Current thread interrupted → force shutdown
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

/* ============================================================
   🔹 COMMON RUNNABLE TASK
   ============================================================ */
class SimpleRunnableTask implements Runnable {

    private final int taskId;

    SimpleRunnableTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {

        System.out.println(
            "Task " + taskId +
            " started on " +
            Thread.currentThread().getName()
        );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Task " + taskId + " interrupted");
            Thread.currentThread().interrupt();
        }

        System.out.println(
            "Task " + taskId +
            " finished on " +
            Thread.currentThread().getName()
        );
    }
}

/* ============================================================
   1️⃣ SINGLE THREAD POOL
   ============================================================ */
class SingleThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5; i++) {
            executor.submit(new SimpleRunnableTask(i));
        }
        System.out.println("Taks Submitted");
        ThreadPoolSample.shutdownAndAwait(executor);
    }
}

/* ============================================================
   2️⃣ FIXED THREAD POOL
   ============================================================ */
class FixedThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            executor.submit(new SimpleRunnableTask(i));
        }

        ThreadPoolSample.shutdownAndAwait(executor);
    }
}

/* ============================================================
   3️⃣ CACHED THREAD POOL
   ============================================================ */
class CachedThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newCachedThreadPool();

        for (int i = 1; i <= 20; i++) {
            executor.submit(new SimpleRunnableTask(i));
        }

        ThreadPoolSample.shutdownAndAwait(executor);
    }
}

/* ============================================================
   4️⃣ SCHEDULED THREAD POOL
   ============================================================ */
class ScheduledThreadPoolDemo {

    public static void main(String[] args) {

        ScheduledExecutorService scheduler =
                Executors.newScheduledThreadPool(2);

        scheduler.schedule(
            new SimpleRunnableTask(1),
            2,
            TimeUnit.SECONDS
        );

        scheduler.scheduleAtFixedRate(
            new SimpleRunnableTask(2),
            1,
            3,
            TimeUnit.SECONDS
        );

        scheduler.scheduleWithFixedDelay(
            new SimpleRunnableTask(3),
            1,
            3,
            TimeUnit.SECONDS
        );

        // Simulate controlled runtime window
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        ThreadPoolSample.shutdownAndAwait(scheduler);
    }
}

/* ============================================================
   5️⃣ WORK STEALING THREAD POOL (DAEMON THREADS)
   ============================================================ */
class WorkStealingPoolDemo {

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newWorkStealingPool();

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.submit(() -> {
                System.out.println(
                    "Work-stealing task " + taskId +
                    " running on " +
                    Thread.currentThread().getName()
                );
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Main thread waits → daemon threads get time to finish
        ThreadPoolSample.shutdownAndAwait(executor);
    }
}

/* ============================================================
   🔹 CALLABLE + FUTURE
   ============================================================ */
class CallableDemo {

    static class SumCallable implements Callable<Integer> {

        private final int a;
        private final int b;

        SumCallable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return a + b;
        }
    }

    public static void main(String[] args) {

        ExecutorService executor =
                Executors.newSingleThreadExecutor();

        Future<Integer> future =
                executor.submit(new SumCallable(10, 20));

        try {
            System.out.println("Result = " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ThreadPoolSample.shutdownAndAwait(executor);
    }
}
