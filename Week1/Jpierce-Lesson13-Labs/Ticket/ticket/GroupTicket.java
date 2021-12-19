package edu.waketech.ticket;

import edu.waketech.ticket.common.Ticket;

public class GroupTicket extends Ticket {
	private double discount;
	

	public GroupTicket(String venue, String event, String date, double basePrice, double discount, int quantity) {
		super(venue, event, date, basePrice);
		super.numOfTickets = quantity;
		this.discount = discount;
	}
	
	@Override
	public double getPrice() {
		double sum = (super.numOfTickets * super.basePrice);
		double sumOfDiscount = sum * discount;
		return sum - sumOfDiscount;
	}
	
	@Override
	public String toString() {
		return "\nVenue: " + venue
			   + "\nEvent: " + event
			   + "\nDate: " + date
			   + "\nPrice Per Ticket: $" + basePrice
			   + "\nNumber Of Tickets:  " + numOfTickets
			   + "\nDiscount: " + discount * BASE_HUNDRED + "%"
			   + "\n------------------" 
			   + "\nTotal: $" + getPrice();
	}

}
