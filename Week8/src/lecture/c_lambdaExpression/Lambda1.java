package lecture.c_lambdaExpression;
//A method (or function) in Java has these main parts:

//1. Name
//2. Parameter list
//3. Body
//4. return type.
//
//A lambda expression in Java has these main parts:
//Lambda expression only has body and parameter list.
//1. No name – function is anonymous so we don’t care about the name
//2. Parameter list
//3. Body – This is the main part of the function.
//4. No return type – The java 8 compiler is able to infer the return type by checking the code. 
//you need not to mention it explicitly.

// must be functional : only ONE method

public class Lambda1 {

	public static void main(String[] args) {

		I_SimpleGreeting msg = new I_SimpleGreeting(){
			@Override
			public String sayHello() {
				return "Hello";
			}
		};

//		I_SimpleGreeting msg = () -> {
//			return "Hello";
//		};

		System.out.println(msg.sayHello());

	}

}
