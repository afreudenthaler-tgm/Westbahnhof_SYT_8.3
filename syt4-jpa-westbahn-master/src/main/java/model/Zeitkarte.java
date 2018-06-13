package model;
import java.util.Date;

public class Zeitkarte extends Ticket {

	private Date gueltigAb;

	private ZeitkartenTyp typ;

	public Zeitkarte(Strecke strecke, Zahlung zahlung) {
		super(strecke, zahlung);
	}
}
