package edu.waketech.ticket;

import edu.waketech.ticket.common.Ticket;

public class ComplementaryTicket extends Ticket {

	public ComplementaryTicket(String venue, String event, String date) {
		super.venue = venue;
		super.event = event;
		super.date = date;
	}
	
	@Override
	public double getPrice() {
		return 0;
	}

}
