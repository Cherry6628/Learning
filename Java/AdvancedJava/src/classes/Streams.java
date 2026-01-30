package classes;

import java.util.*;
import java.util.stream.*;

/*
 * This class is a NOTEBOOK for Java Streams.
 *
 * Core idea of Streams:
 * --------------------
 * A Stream is:
 *   - NOT a data structure
 *   - NOT storing data
 *   - A PIPELINE that processes data
 *
 * Streams work by:
 *   Source  ->  Intermediate Ops  ->  Terminal Op
 *
 * Streams are built on:
 *   - Predicate
 *   - Function
 *   - Consumer
 *   - Supplier (internally)
 */
public class Streams {

    public static void main(String[] args) {

        /*
         * =========================================================
         * STREAM SOURCE
         * =========================================================
         *
         * Streams always start from a SOURCE.
         * Common sources:
         *   - Collection.stream()
         *   - Arrays.stream()
         *   - Stream.of()
         */

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);


        /*
         * =========================================================
         * filter()  -> uses Predicate<T>
         * =========================================================
         *
         * Predicate<T>:
         *   T -> boolean
         *
         * filter() keeps elements ONLY if predicate returns true.
         */

        Stream<Integer> evenNumbersStream = numbers.stream()
                .filter(n -> n % 2 == 0); // Predicate<Integer>

        /*
         * IMPORTANT:
         * At this point NOTHING has executed.
         * Streams are LAZY.
         */

        evenNumbersStream.forEach(n -> System.out.println("Even: " + n));
        // forEach() is TERMINAL → execution happens here


        /*
         * =========================================================
         * map()  -> uses Function<T, R>
         * =========================================================
         *
         * Function<T, R>:
         *   T -> R
         *
         * map() TRANSFORMS elements.
         */

        numbers.stream()
                .map(n -> n * n)   // Function<Integer, Integer>
                .forEach(n -> System.out.println("Square: " + n));


        /*
         * =========================================================
         * filter + map + forEach (typical pipeline)
         * =========================================================
         *
         * This shows how streams chain functional interfaces.
         */

        numbers.stream()
                .filter(n -> n > 3)          // Predicate
                .map(n -> "Value: " + n)     // Function
                .forEach(System.out::println); // Consumer


        /*
         * =========================================================
         * collect()  -> TERMINAL operation
         * =========================================================
         *
         * collect() transforms a Stream back into a Collection.
         */

        List<Integer> doubledEvens = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 2)
                .collect(Collectors.toList());

        System.out.println("Doubled evens: " + doubledEvens);


        /*
         * =========================================================
         * reduce()  -> uses BiFunction / BinaryOperator
         * =========================================================
         *
         * reduce() combines elements into ONE value.
         */

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
                // BiFunction<Integer, Integer, Integer>

        System.out.println("Sum: " + sum);


        /*
         * =========================================================
         * anyMatch / allMatch / noneMatch
         * =========================================================
         *
         * These are TERMINAL operations.
         * They SHORT-CIRCUIT (stop early).
         */

        boolean anyGreaterThanFive = numbers.stream()
                .anyMatch(n -> n > 5); // Predicate

        System.out.println("Any > 5? " + anyGreaterThanFive);


        /*
         * =========================================================
         * findFirst() / findAny()
         * =========================================================
         *
         * Returns Optional<T>
         */

        Optional<Integer> firstEven = numbers.stream()
                .filter(n -> n % 2 == 0)
                .findFirst();

        firstEven.ifPresent(n -> System.out.println("First even: " + n));


        /*
         * =========================================================
         * IMPORTANT RULES OF STREAMS (DON'T IGNORE)
         * =========================================================
         *
         * 1. Streams are LAZY
         * 2. Streams are ONE-TIME use
         * 3. Streams do NOT modify source
         * 4. Order matters (unless parallel)
         * 5. Terminal operation triggers execution
         */

        /*
         * This would FAIL:
         *
         * Stream<Integer> s = numbers.stream();
         * s.forEach(System.out::println);
         * s.forEach(System.out::println); // IllegalStateException
         */


        /*
         * =========================================================
         * PARALLEL STREAM (DANGEROUS IF MISUSED)
         * =========================================================
         */

        numbers.parallelStream()
                .map(n -> {
                    // May run on different threads
                    System.out.println(Thread.currentThread().getName() + " processing " + n);
                    return n * 2;
                })
                .forEach(System.out::println);
    }
}
