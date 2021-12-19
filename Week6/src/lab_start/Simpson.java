package lab_start;

public class Simpson implements Measurable{
	
	private int id;
	private String imgurl;
	private double rating;
	private int votes;
	private int numSeason; //5
	private int numSeries;
	private String date;
	private int year;
	private int season;
	private String title; //10
	private double views;
	
	
	public Simpson() {}
	
	public Simpson(int id, String img, double rating, int votes, int numSeason, int series, String date,
			int year, int season, String title, double views) {
		
		this.id = id;
		this.imgurl = img;
		this.rating = rating;
		this.votes = votes;
		this.numSeason = numSeason;
		this.numSeries = series;
		this.date = date;
		this.year = year;
		this.season = season;
		this.title = title;
		this.views = views;
	}
	
	
	@Override
	public String toString() {
		return "The Simpsons [Title=" + title + ", Season=" + season + ", Date=" + date + " " + year + ", imdbRating=" + rating
				+ ", imdbVotes=" + votes + "]";
	}
	
	@Override
	public int getMeasure() {
		return votes;
	}

}
