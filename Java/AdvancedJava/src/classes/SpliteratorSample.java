package classes;

import java.util.Spliterator;
import java.util.ArrayList;


public class SpliteratorSample {
	public static void main(String[]args) throws InterruptedException {
		ArrayList<Integer> arr = new ArrayList<>();
		Spliterator<Integer> spl = arr.spliterator();

		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);
		arr.add(5);
		arr.add(6);
		arr.add(7);
		arr.add(8);
		arr.add(9);
		arr.add(10);
		
		Spliterator<Integer> firstPart = spl.trySplit();
		Spliterator<Integer> nextPart = spl.trySplit();
		firstPart.forEachRemaining((el)->{
			System.out.println(el);
		});
		System.out.println("...");
		firstPart.forEachRemaining((el)->{
			System.out.println(el);
		});
		System.out.println("....");

		nextPart.forEachRemaining((el)->{
			System.out.println(el);
		});
		System.out.println("...");
		nextPart.forEachRemaining((el)->{
			System.out.println(el);
		});
		spl.forEachRemaining((el)->System.out.println(el));
		boolean first = spl.tryAdvance((el)->{
			System.out.println(el);
		});
		System.out.println(first);

		
		
		
		
		Spliterator<Integer> spl1 = arr.spliterator();
		for (int i=11;i<10001;i++)arr.add(i);
		Thread t1 = new Thread(()->{
			spl1.forEachRemaining((el)->System.out.println(el));
		});
		Thread t2 = new Thread(()->{
			spl1.forEachRemaining((el)->System.out.println(el));
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
	}
}
