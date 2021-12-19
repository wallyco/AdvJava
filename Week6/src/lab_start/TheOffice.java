package lab_start;

public class TheOffice implements Measurable{
	
	private int record;
	private int season;
	private String episode;
	private String about;
	private Double rating; //5
	private int votes;
	private Double viewership;
	private int duration;
	private String date;
	private String guest; // 10
	private String director;
	private String writers;
	
	public TheOffice() {}
	
	public TheOffice(int rec, int sea, String episode, String about, double rating, int votes,
			double views, int duration, String date, String guest, String director, String writers) {
		this.record = rec;
		this.season = sea;
		this.episode = episode;
		this.about = about;
		this.rating = rating;
		this.votes = votes;
		this.viewership = views;
		this.duration = duration;
		this.date = date;
		this.guest = guest;
		this.director = director;
		this.writers = writers;
	}

	
	@Override
	public String toString() {
		return "The Office [Record Number=" + record + ", Season=" + season + ", Title=" + episode + ", rating=" + rating
				+ ", Votes=" + votes + ", Director=" + director + ", Writers=" + writers + "]";
	}
	
	@Override
	public int getMeasure() {
		return votes;
	}

}
