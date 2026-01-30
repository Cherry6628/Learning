package classes;

import java.util.function.*;

/*
 * All interfaces used here are from:
 *   java.util.function
 *
 * Key idea:
 *   Functional Interface = exactly ONE abstract method
 *   → can be implemented using lambda expressions
 */
/*
 * | Interface  | Method     |
 * | ---------- | ---------- |
 * | Consumer   | `accept()` |
 * | BiConsumer | `accept()` |
 * | Function   | `apply()`  |
 * | BiFunction | `apply()`  |
 * | Predicate  | `test()`   |
 * | Supplier   | `get()`    |

*/
public class SomeFunctionalInterfacesSample {

    public static void main(String[] args) {

        /*
         * =========================================================
         * 1. Consumer<T>
         * =========================================================
         *
         * Signature:
         *   T -> void
         *
         * Meaning:
         *   - Takes ONE input
         *   - Does something with it
         *   - Returns NOTHING
         *
         * Think:
         *   "I consume the value and cause side effects"
         */

        Consumer<String> printConsumer = (String s) -> {
            // Side effect: printing to console
            System.out.println("Consumer received: " + s);
        };

        // Calling accept() executes the lambda
        printConsumer.accept("Hello Consumer");


        /*
         * =========================================================
         * 2. BiConsumer<T, U>
         * =========================================================
         *
         * Signature:
         *   (T, U) -> void
         *
         * Meaning:
         *   - Takes TWO inputs
         *   - Performs side effects
         *   - Returns NOTHING
         *
         * Common in:
         *   Map.forEach((k, v) -> ...)
         */

        BiConsumer<String, Integer> nameAgeConsumer = (name, age) -> {
            System.out.println("Name: " + name + ", Age: " + age);
        };

        nameAgeConsumer.accept("ZerilPHA", 17);


        /*
         * =========================================================
         * 3. Function<T, R>
         * =========================================================
         *
         * Signature:
         *   T -> R
         *
         * Meaning:
         *   - Takes ONE input
         *   - Transforms it
         *   - Returns a result
         *
         * SHOULD be:
         *   - Pure (no side effects)
         *   - Deterministic
         *
         * Backbone of streams (map)
         */

        Function<Integer, String> intToString = (Integer i) -> {
            return "Number is: " + i;
        };

        String result = intToString.apply(10);
        System.out.println(result);


        /*
         * =========================================================
         * 4. BiFunction<T, U, R>
         * =========================================================
         *
         * Signature:
         *   (T, U) -> R
         *
         * Meaning:
         *   - Takes TWO inputs
         *   - Produces ONE output
         *
         * Used for:
         *   - Combining values
         *   - Merge logic
         */

        BiFunction<Integer, Integer, Integer> addFunction = (a, b) -> {
            return a + b;
        };

        int sum = addFunction.apply(5, 7);
        System.out.println("Sum: " + sum);


        /*
         * =========================================================
         * 5. Predicate<T>
         * =========================================================
         *
         * Signature:
         *   T -> boolean
         *
         * Meaning:
         *   - Tests a condition
         *   - Returns true or false
         *
         * Used in:
         *   - filter()
         *   - validations
         *   - guards
         *
         * NOTE:
         *   Predicate is NOT Function<T, Boolean>
         *   It exists for semantic clarity.
         */

        Predicate<Integer> isEvenPredicate = (Integer n) -> {
            return (n & 1) == 0;
        };

        boolean isEven = isEvenPredicate.test(4);
        System.out.println("Is even? " + isEven);


        /*
         * =========================================================
         * 6. Supplier<T>
         * =========================================================
         *
         * Signature:
         *   () -> T
         *
         * Meaning:
         *   - Takes NO input
         *   - Produces a value
         *
         * Used for:
         *   - Lazy creation
         *   - Factories
         *   - Deferred execution
         */

        Supplier<Double> randomSupplier = () -> {
            // Value created only when get() is called
            return Math.random();
        };

        Double randomValue = randomSupplier.get();
        System.out.println("Random value: " + randomValue);
        randomValue = randomSupplier.get();
        System.out.println("Random value: " + randomValue);
    }
}
