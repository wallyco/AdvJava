package lecture.e_predicate;

import java.util.function.Predicate;

public class Example2Main {

	public static void main(String[] args) {
		
		// Using Predicate interface
		Predicate<Integer> p1 = Example2::checkAge1;		
		// Calling Predicate method
		boolean result1 = p1.test(25);
		System.out.println(result1);
		
		
		Predicate<Integer> p2 = Example2::checkAge2;		
		// Calling Predicate method
		boolean result = p2.test(25);
		System.out.println(result);
	}
}