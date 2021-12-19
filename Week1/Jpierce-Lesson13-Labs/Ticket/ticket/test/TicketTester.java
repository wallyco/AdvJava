package edu.waketech.ticket.test;

import edu.waketech.ticket.*;
import edu.waketech.ticket.common.Ticket;

public class TicketTester {

	public static void main(String[] args) {
		Ticket t;
		
        t = new AdvanceTicket("venue1", "party", "1/1/16", 10, .1);
        System.out.print(evalTicket(t));
        System.out.println(t.toString());
        System.out.println();
       
        t = new ComplementaryTicket("venue2", "show", "1/2/16");
        System.out.print(evalTicket(t));
        System.out.println(t.toString());
        System.out.println();
       
        t = new DayOfTicket("venue3", "club", "1/3/16", 11.0);
        System.out.print(evalTicket(t));
        System.out.println(t.toString());
        System.out.println();
       
        t = new GroupTicket("venue4", "reunion", "1/4/16", 12, .15, 8);
        System.out.print(evalTicket(t));
        System.out.println(t.toString());
        System.out.println();
	}
	
	  public static String evalTicket(Ticket t) {
	        String s = "event: " + t.getEvent() + "\n";
	        s += "venue: " + t.getVenue() + "\n";
	        s += "date: " + t.getDate() + "\n";
	        s += "number of holders: " + t.getNumberOfHolders() + "\n";
	        s += "base price: " + t.getBasePrice() + "\n";
	        s += "price: " + t.getPrice() + "\n";
	        return s;
	    }

}
