package lecture.b_anonymousClasses;

public class HelloWorldAnonMethods {

	public void sayHello() {
		// the method without anonymous classes
		EnglishGreeting englishGreeting = new EnglishGreeting();

		// create an anonymous class based on an interface
		// since based on an interface is must implement the greet and greetSomeone
		I_HelloWorld frenchGreeting = new I_HelloWorld() {
			String name = "tout le monde";
			public void greet() {
				greetSomeone("tout le monde");
			}
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Salut " + name);
			}
		};

		// create an anonymous class based on an interface
		// since based on an interface is must implement the greet and greetSomeone
		I_HelloWorld spanishGreeting = new I_HelloWorld() {
			String name = "mundo";
			public void greet() {
				greetSomeone("mundo");
			}
			public void greetSomeone(String someone) {
				name = someone;
				System.out.println("Hola, " + name);
			}
		};
		
		englishGreeting.greet();
		frenchGreeting.greetSomeone("Fred");
		spanishGreeting.greet();
	}

}