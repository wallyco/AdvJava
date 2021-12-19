package lecture.e_predicate;

//It is a functional interface which represents a predicate 
//(boolean-valued function) of one argument. 
//It is defined in the java.util.function package and contains test() a functional method.	
//There are some predefined functional interface in Java like Predicate, consumer, supplier etc. 


// RYO - Role your own


import java.util.function.Predicate;

public class Example1 {

	interface PredicateRYO {
		public boolean testRYO(int x);
	}

	public static void main(String[] args) {
		// Anonymous inner class - functional interface
		PredicateRYO pOldWay = new PredicateRYO() {
			@Override
			public boolean testRYO(int x) {
				if (x > 12)
					return true;
				else
					return false;
			}
		};
		System.out.println("Old way - " + pOldWay.testRYO(1));
		
		// Newer way with lambda - the lambda implements the testRYO
		PredicateRYO p = a -> (a > 18);
		System.out.println(p.testRYO(25));

		// Using the Predicate Java Function
		Predicate<Integer> pr = a -> (a > 18); // Creating predicate
		System.out.println(pr.test(10)); // Calling Predicate method

		Predicate<String> ps = a -> (a.length() > 5); // Creating predicate
		System.out.println(ps.test("George")); // Calling Predicate method

	}
}