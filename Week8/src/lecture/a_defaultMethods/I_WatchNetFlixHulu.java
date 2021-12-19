package lecture.a_defaultMethods;

public interface I_WatchNetFlixHulu {
	// This method MUST BE over ridden in an Implementation
	// they can NOT have a method body
	void watchNetFlix();

	// default methods CAN BE over ridden in an Implementation
	// they must have a method body
	default void watchHulu() {
		System.out.println("watch Hulu default method");
	};
}
