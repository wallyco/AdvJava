package lecture.b_anonymousClasses;

public class AnonClassTester {

	public static void main(String[] args) {
		
		//Anonymous classes enable you to make your code more concise. 

		//They enable you to declare and instantiate a class at the same time. 
		//They are like local classes except that they do not have a name. 
		//Use them if you need to use a local class only once.
		
		
		HelloWorldAnonMethods myApp = new HelloWorldAnonMethods();
		myApp.sayHello();

		/*
		 * Here are some things to ponder when you work with anonymous classes:
		 * 
		 * You can’t create a constructor for an anonymous class, because the anonymous
		 * class doesn’t have a name. What would you call the constructor, anyway?
		 * 
		 * You can’t pass parameters if the anonymous class is based on an interface.
		 * That makes sense; interfaces don’t have constructors, so Java wouldn’t have
		 * anything to pass the parameters to.
		 * 
		 * An assignment statement can use an anonymous class as shown in this example.
		 * In that case, the anonymous class body is followed by a semicolon that marks
		 * the end of the assignment statement. Note that this semicolon is part of the
		 * assignment statement, not the anonymous class.
		 * 
		 * An anonymous class is a special type of inner class; like any inner class, it
		 * automatically has access to the fields and methods of its outer class.
		 * 
		 * An anonymous class can’t be static.
		 */		
	}
}
