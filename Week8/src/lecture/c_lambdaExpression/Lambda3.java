package lecture.c_lambdaExpression;

public class Lambda3 {
	interface StringConcat {
		public String sconcat(String a, String b);
	}

	public static void main(String args[]) {
		// lambda expression with multiple arguments
//		StringConcat s = new StringConcat() {
//			@Override
//			public String sconcat(String a, String b) {
//				return a + b;
//			}
//		};
		
		StringConcat s = (str1, str2) -> str1 + str2;
		System.out.println("Result: " + s.sconcat("Hello ", "World"));
	}
}