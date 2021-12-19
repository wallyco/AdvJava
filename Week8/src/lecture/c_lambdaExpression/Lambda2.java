package lecture.c_lambdaExpression;

public class Lambda2 {
	interface UpFiveInterface {
		public int incrementByFive(int a);
	}

	public static void main(String args[]) {
		// lambda expression with single parameter num
//		UpFiveInterface f = new UpFiveInterface() {
//			@Override
//			public int incrementByFive(int a) {
//				return a + 5;
//			}
//		};

		
		UpFiveInterface f = (num) -> num+5;
		System.out.println(f.incrementByFive(22));
	}
}
