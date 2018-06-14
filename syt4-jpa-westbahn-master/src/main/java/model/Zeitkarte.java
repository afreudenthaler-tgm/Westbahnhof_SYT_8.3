package model;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Zeitkarte extends Ticket {

	private Date gueltigAb;

	private ZeitkartenTyp typ;



	public Zeitkarte() {}

	public Zeitkarte(ZeitkartenTyp typ, Date gueltigAb, Strecke strecke, Zahlung zahlung) {
		super(strecke,zahlung);
		this.typ = typ;
		this.gueltigAb = gueltigAb;
	}

	public Date getGueltigAb() {
		return gueltigAb;
	}

	public void setGueltigAb(Date gueltigAb) {
		this.gueltigAb = gueltigAb;
	}

	public ZeitkartenTyp getTyp() {
		return typ;
	}

	public void setTyp(ZeitkartenTyp typ) {
		this.typ = typ;
	}
}
