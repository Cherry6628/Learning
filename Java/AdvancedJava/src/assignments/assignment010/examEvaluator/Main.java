package assignments.assignment010.examEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ExamEvaluator implements Callable<String> {
    private final String student;

    public ExamEvaluator(String student) {
        this.student = student;
    }

    public String call() throws Exception {
        String teacher = Thread.currentThread().getName();
        System.out.println(teacher + " grading " + student);
        Thread.sleep(500);
        int score = new Random().nextInt(101);
        return "Student: " + student + " | Score: " + score + " | Graded by: " + teacher;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> results = new ArrayList<>();

        String[] students = {"Student1", "Student2", "Student3", "Student4", "Student5"};

        for (String s : students) {
            results.add(executor.submit(new ExamEvaluator(s)));
        }

        for (Future<String> f : results) {
            System.out.println(f.get());
        }

        executor.shutdown();
    }
}
