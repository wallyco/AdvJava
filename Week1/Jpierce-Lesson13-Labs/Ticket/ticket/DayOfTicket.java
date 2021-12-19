package edu.waketech.ticket;

import edu.waketech.ticket.common.Ticket;

public class DayOfTicket extends Ticket {
	
	public DayOfTicket(String venue, String event, String date, double basePrice) {
		super(venue, event, date, basePrice);
	}
	
	@Override
	public double getPrice() {
		return super.basePrice * super.numOfTickets;
	}

}
