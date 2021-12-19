package edu.waketech.ticket.common;

public abstract class Ticket {
	
	protected static final double BASE_HUNDRED = 100;
	protected double basePrice;
	protected int numOfTickets = 1;
	
	protected String venue;
	protected String event;
	protected String date;

	
	
	public Ticket(String venue, String event, String date, double basePrice) {
		this.venue = venue;
		this.event = event;
		this.date = date;
		this.basePrice = basePrice;
	}
	
	public Ticket() { }
	
	public int getNumberOfHolders() {
		return numOfTickets;
	}
	
	@Override
	public String toString() {
		return "\nVenue: " + venue
			   + "\nEvent: " + event
			   + "\nDate: " + date
			   + "\nPrice Per Ticket: $" + basePrice
			   + "\nNumber Of Tickets:  " + numOfTickets
			   + "\n------------------" 
			   + "\nTotal: $" + getPrice()
			   + "\n";	   
	}
	
	public abstract double getPrice();

	public String getVenue() {
		return venue;
	}

	public String getEvent() {
		return event;
	}

	public String getDate() {
		return date;
	}

	public double getBasePrice() {
		return basePrice;
	}
	
}
