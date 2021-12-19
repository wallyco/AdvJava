package lecture.a_defaultMethods;

public class Implementation implements I_WatchNetFlixHulu, I_WatchPrimeVevoYouTube {

	@Override
	public void watchAmazon() {
		System.out.println("watch Amazon Prime - required to be implemented");
	}
	@Override
	public void watchNetFlix() {
		System.out.println("watch NetFlix - required to be implemented");
	}
	public void watchYouTube() {
		System.out.println("watch youtube override");
	}
	public void watchVevo() {
		System.out.println("watch vevo override");
	}
}
