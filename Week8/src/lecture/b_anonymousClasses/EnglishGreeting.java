package lecture.b_anonymousClasses;

public class EnglishGreeting implements I_HelloWorld {
	String name = "world";

	public void greet() {
		greetSomeone("world");
	}

	public void greetSomeone(String someone) {
		name = someone;
		System.out.println("Hello " + name);
	}
}
