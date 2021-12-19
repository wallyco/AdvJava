package lecture.a_defaultMethods;

public interface I_WatchPrimeVevoYouTube {
	// This method MUST BE over ridden in an Implementation
	// they can NOT have a method body
	void watchAmazon();

	// default methods CAN BE over ridden in an Implementation
	// they MUST have a method body
	default void watchVevo() {
		System.out.println("watch Vevo - default method");
	};

	// static methods accessed directly from the interface
	// they MUST have a method body
	static void watchYouTube() {
		System.out.println("watch youtube - direct from the interface");
	};
}
