package dojo.bpm.camunda.coffee.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Bestellung {

	public enum Bestellstatus {
		NEU, GENEHMIGT, ABGEWIESEN, FERTIG
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(nullable = false)
	private BigDecimal gesamtpreis;

	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Bestellstatus status;

	@Column(nullable = false, length = 100)
	private String besteller;

	@Column(nullable = false, length = 100)
	private String genehmiger;

	@ManyToMany
	@JoinTable(name = "BestellungKaffes", joinColumns = { @JoinColumn(name = "bestellungId", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "kaffeeId", referencedColumnName = "id") })
	private List<Kaffee> kaffees = new ArrayList<Kaffee>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBesteller() {
		return besteller;
	}

	public void setBesteller(String besteller) {
		this.besteller = besteller;
	}

	public BigDecimal getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(BigDecimal gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	public String getGenehmiger() {
		return genehmiger;
	}

	public void setGenehmiger(String genehmiger) {
		this.genehmiger = genehmiger;
	}

	public Bestellstatus getStatus() {
		return status;
	}

	public void setStatus(Bestellstatus status) {
		this.status = status;
	}

	public List<Kaffee> getKaffees() {
		return Collections.unmodifiableList(kaffees);
	}

	public void addKaffee(Kaffee kaffee) {
		this.kaffees.add(kaffee);
	}

}
