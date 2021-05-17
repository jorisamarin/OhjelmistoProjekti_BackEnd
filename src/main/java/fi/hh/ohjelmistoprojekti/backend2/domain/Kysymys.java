package fi.hh.ohjelmistoprojekti.backend2.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table (name = "kysymykset")
public class Kysymys {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kysymys_id;
	private String kysymystyyppi;
	private String kysymysteksti;
	
	@ManyToOne
	@JoinColumn(name="kyselyId")
	private Kysely kysely;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vastaus> vastaukset;
	
	
	
	public Kysymys(Long kysymys_id, String kysymystyyppi, String kysymysteksti) {
		super();
		this.kysymys_id = kysymys_id;
		this.kysymystyyppi = kysymystyyppi;
		this.kysymysteksti = kysymysteksti;
	}

	public Kysymys() {
	}
	
	public Kysymys(String kysymystyyppi, String kysymysteksti) {
		super();
		this.kysymystyyppi = kysymystyyppi;
		this.kysymysteksti = kysymysteksti;
	}
	
	/*
	public Kysymys(String kysymystyyppi, String kysymysteksti, Kysely kysely, List<Vastaus> vastaukset) {
		super();
		this.kysymystyyppi = kysymystyyppi;
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
		this.vastaukset = vastaukset;
	} */

	public Long getKysymys_id() {
		return kysymys_id;
	}

	public void setKysymys_id(Long kysymys_id) {
		this.kysymys_id = kysymys_id;
	}
	@Column (name = "kysymystyyppi", nullable = false)
	public String getKysymystyyppi() {
		return kysymystyyppi;
	}

	public void setKysymystyyppi(String kysymystyyppi) {
		this.kysymystyyppi = kysymystyyppi;
	}

	public String getKysymysteksti() {
		return kysymysteksti;
	}

	public void setKysymysteksti(String kysymysteksti) {
		this.kysymysteksti = kysymysteksti;
	}

	public Kysely getKysely() {
		return kysely;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	public void setVastaus(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}

	@Override
	public String toString() {
		return "Kysymys [kysymys_id=" + kysymys_id + ", kysymystyyppi=" + kysymystyyppi + ", kysymysteksti=" + kysymysteksti
				+ ", kysely_id=" + kysely + ", vastaukset=" + vastaukset + "]";
	}

}