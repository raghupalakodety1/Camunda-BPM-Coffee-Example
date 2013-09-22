package dojo.bpm.camunda.coffee.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Kaffee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = false)
	private BigDecimal preis;
	@Column(length = 500)
	private String kommentar;

	public Kaffee() {
	}

	public Kaffee(String name, BigDecimal preis, String kommentar) {
		this.name = name;
		this.preis = preis;
		this.kommentar = kommentar;
	}

	@ManyToMany(mappedBy = "kaffees")
	private List<Bestellung> bestellungen = new ArrayList<Bestellung>();

	public long getId() {
		return id;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public String getName() {
		return name;
	}

	public String getKommentar() {
		return kommentar;
	}

	public List<Bestellung> getBestellungen() {
		return Collections.unmodifiableList(bestellungen);
	}

	void addBestellung(Bestellung bestellung) {
		this.bestellungen.add(bestellung);
	}

}
