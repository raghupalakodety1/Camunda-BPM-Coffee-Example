package dojo.bpm.camunda.coffee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="bestellung")
public class Bestellung {

	@Id
	@GeneratedValue
	@Column(name="bestId", nullable=false)
	private long bestId;
	private String besteller;
	private long gesamtpreis;
	private String genehmiger;
	private String status; //possible values n,g,a,f
	
	public long getId() {
		return bestId;
	}
	public void setId(long id) {
		this.bestId = id;
	}
	public String getBesteller() {
		return besteller;
	}
	public void setBesteller(String besteller) {
		this.besteller = besteller;
	}
	public long getGesamtpreis() {
		return gesamtpreis;
	}
	public void setGesamtpreis(long gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}
	public String getGenehmiger() {
		return genehmiger;
	}
	public void setGenehmiger(String genehmiger) {
		this.genehmiger = genehmiger;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
