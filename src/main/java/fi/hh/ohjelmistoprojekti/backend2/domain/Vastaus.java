package fi.hh.ohjelmistoprojekti.backend2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table (name = "vastaukset")
public class Vastaus {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long vastaus_id;
	private String vastaus;
	
	
	@ManyToOne
	@JoinColumn(name="kysymys_id")
	private Kysymys kysymys;
	
	public Vastaus(String vastaus, Kysymys kysymys) {
		super();
		this.vastaus = vastaus;
		this.kysymys = kysymys;
	}
	
	public Vastaus() {
		super();
	}

	
	public Long getVastaus_id() {
		return vastaus_id;
	}

	public void setVastaus_id(Long vastaus_id) {
		this.vastaus_id = vastaus_id;
	}

	@Column (name ="vastaus", nullable = false)
	public String getVastaus() {
		return vastaus;
	}

	public void setVastaus(String vastaus) {
		this.vastaus = vastaus;
	}

	public Kysymys getKysymys() {
		return kysymys;
	}

	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

}

