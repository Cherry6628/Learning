package assignments.assignment011;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.*;

public class Ass11 {
	public static void main(String[] args) {
		thePrinter();
		theMapLooper();
		theFilter();
		theGenerator();
		theTransformer();
		theSalaryAdjuster();
	}

	public static void thePrinter() {
		List<String> names = List.of("John", "Jane", "Jack");
		Consumer<String> c = System.out::println;
		names.stream().forEach(c);
	}

	public static void theMapLooper() {
		Map<String, Integer> items = Map.of("Apples", 10, "Bananas", 20, "Oranges", 5);
		BiConsumer<String, Integer> bc = (k, v) -> System.out.println("We have " + v + " " + k);
		items.forEach(bc);
	}

	public static void theFilter() {
		List<Integer> numbers = List.of(10, 55, 23, 8, 90);
		Predicate<Integer> p = n -> n > 20;
		List<Integer> filtered = numbers.stream().filter(p).toList();
		System.out.println(filtered);
	}

	public static void theGenerator() {
		Random random = new Random();
		Supplier<Integer> s = () -> random.nextInt(100);

		List<Integer> randoms = Stream.generate(s).limit(5).toList();
		randoms.forEach(System.out::println);
	}

	public static void theTransformer() {
		List<String> words = List.of("java", "is", "cool");
		Function<String, String> f = String::toUpperCase;

		List<String> upper = words.stream().map(f).toList();
		System.out.println(upper);
	}

	public static void theSalaryAdjuster() {
		Map<String, Integer> salaries = Map.of("Alice", 50000, "Bob", 60000);
		BiFunction<String, Integer, Integer> bf = (name, salary) -> (int) (salary * 1.10);

		Map<String, Integer> raisedSalaries = salaries.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> bf.apply(e.getKey(), e.getValue())));

		System.out.println(raisedSalaries);
	}
}