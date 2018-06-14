package model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Strecke {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;


	@ManyToOne
	private Bahnhof start;

	@ManyToOne
	private Bahnhof bahnhof;


	@ManyToOne
	private Bahnhof ende;

	public Strecke(Bahnhof start, Bahnhof ende) {
		this.start = start;
		this.ende = ende;
	}

	public Strecke(Bahnhof start, Bahnhof bahnhof, Bahnhof ende) {
		this.start = start;
		this.bahnhof = bahnhof;
		this.ende = ende;
	}

	public Strecke() {
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public Bahnhof getStart() {
		return start;
	}

	public void setStart(Bahnhof start) {
		this.start = start;
	}

	public Bahnhof getBahnhof() {
		return bahnhof;
	}

	public void setBahnhof(Bahnhof bahnhof) {
		this.bahnhof = bahnhof;
	}

	public Bahnhof getEnde() {
		return ende;
	}

	public void setEnde(Bahnhof ende) {
		this.ende = ende;
	}
}
