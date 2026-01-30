package classes;

@FunctionalInterface
interface Task<T> {

    // Single abstract method → makes it a functional interface
    T doCalculation(T data);

    // Default method does NOT affect functional interface rule
    default T same(T data) {
        return data;
    }
}

public class FunctionalInterfaceSample {

    // Generic method that accepts a Task and a value
    public static <R> R execute(Task<R> task, R value) {
        return task.doCalculation(value);
    }

    public static void main(String[] args) {

        // Lambda expression
        Task<Integer> square = n -> n * n;

        System.out.println(square.doCalculation(5)); // direct call

        // Passing the functional interface instance
        System.out.println(execute(square, 5));

        // Passing a METHOD REFERENCE
        System.out.println(execute(square::doCalculation, 5));
        System.out.println(execute(n -> n * n, 5));
    }
}
