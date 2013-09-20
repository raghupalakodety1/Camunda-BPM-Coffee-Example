package dojo.bpm.camunda.coffee.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity(name="kaffee")
public class Kaffee {

	@Id
	@GeneratedValue
	@Column(name="kaffeeId", nullable=false)
	private long kaffeeId;
	private float preis;
	private String name;
	private String kommentar;
	
	private Set<Bestellung> bestellungen = new HashSet<Bestellung>();
	public long getId() {
		return kaffeeId;
	}
	public void setId(long id) {
		this.kaffeeId = id;
	}
	public float getPreis() {
		return preis;
	}
	public void setPreis(float preis) {
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
	
	@ManyToMany
	@JoinTable(name="kaffee-pro-bestellung", 
		joinColumns= {@JoinColumn(name="kaffeeId")},
		inverseJoinColumns={@JoinColumn(name="bestellungId") })
	public Set<Bestellung> getBestellungen() {
		return bestellungen;
	}
	public void setBestellungen(Set<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
	
	
	
}

//kaffee-pro-bestellung
//id-k
//id-b
//menge
