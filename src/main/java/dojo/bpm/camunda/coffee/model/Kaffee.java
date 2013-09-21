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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = false)
	private BigDecimal preis;
	@Column(length = 500)
	private String kommentar;

	@ManyToMany(mappedBy = "kaffees")
	private List<Bestellung> bestellungen = new ArrayList<Bestellung>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public List<Bestellung> getBestellungen() {
		return Collections.unmodifiableList(bestellungen);
	}

	public void addBestellung(Bestellung bestellung) {
		this.bestellungen.add(bestellung);
	}

}
