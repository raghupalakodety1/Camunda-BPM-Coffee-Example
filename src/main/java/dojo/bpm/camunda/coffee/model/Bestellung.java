package dojo.bpm.camunda.coffee.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bestellung {

	public enum Bestellstatus {
		NEU, GENEHMIGT, ABGEWIESEN, FERTIG
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date bestelldatum;

	@Column(nullable = false)
	private BigDecimal gesamtpreis;

	@Column(nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private Bestellstatus status;

	@Column(nullable = false, length = 100)
	private String besteller;

	@Column(nullable = true, length = 100)
	private String genehmiger;

	@ManyToMany
	@JoinTable(name = "BestellungKaffes", joinColumns = { @JoinColumn(name = "bestellungId", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "kaffeeId", referencedColumnName = "id") })
	private List<Kaffee> kaffees = new ArrayList<Kaffee>();

	public Bestellung() {
	}

	public Bestellung(BigDecimal gesamtpreis, String besteller,
			List<Kaffee> kaffees) {
		this.status = Bestellstatus.NEU;
		this.bestelldatum = new Date();
		this.gesamtpreis = gesamtpreis;
		this.besteller = besteller;
		for (Kaffee kaffee : kaffees) {
			addKaffee(kaffee);
		}
	}

	public long getId() {
		return id;
	}

	public Date getBestelldatum() {
		return bestelldatum;
	}

	public String getBesteller() {
		return besteller;
	}

	public BigDecimal getGesamtpreis() {
		return gesamtpreis;
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

	public void setNewStatus(Bestellstatus newStatus) {
		switch (this.status) {
		case FERTIG:
		case ABGEWIESEN:
			throw new IllegalStateException("finaler Status " + this.status
					+ " kann nicht geändert werden");
		case GENEHMIGT:
			if (newStatus == Bestellstatus.NEU) {
				throw new IllegalStateException("finaler Status " + this.status
						+ " kann nicht geändert werden");
			}
			break;
		case NEU:
			break;
		}
		this.status = newStatus;
	}

	public List<Kaffee> getKaffees() {
		return Collections.unmodifiableList(kaffees);
	}

	public void addKaffee(Kaffee kaffee) {
		if (!kaffees.contains(kaffee)) {
			this.kaffees.add(kaffee);
		}
		if (!kaffee.getBestellungen().contains(this)) {
			kaffee.addBestellung(this);
		}
	}

}
