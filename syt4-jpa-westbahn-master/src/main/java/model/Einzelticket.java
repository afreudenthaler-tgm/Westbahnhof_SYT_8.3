package model;

import javax.persistence.Entity;

@Entity
public class Einzelticket extends Ticket {

	private TicketOption ticketOption;



	public Einzelticket(Strecke strecke, Zahlung zahlung) {
		super(strecke, zahlung);
	}

	public Einzelticket() {
	}

	public TicketOption getTicketOption() {
		return ticketOption;
	}

	public void setTicketOption(TicketOption ticketOption) {
		this.ticketOption = ticketOption;
	}
}
