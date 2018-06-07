package model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Ticket {

	@Id
	@GeneratedValue
	protected Long ID;

	protected Strecke strecke;

	protected Zahlung zahlung;

	public Ticket(Strecke strecke, Zahlung zahlung) {
		this.strecke = strecke;
		this.zahlung = zahlung;
	}
}
