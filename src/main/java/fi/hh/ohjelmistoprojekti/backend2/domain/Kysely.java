package fi.hh.ohjelmistoprojekti.backend2.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Kysely {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kyselyId;
	private String nimi;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private User user;
	
	@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "kysymys_id")
    private List<Kysymys> kysymykset;
	
	

	public Kysely() {
		super();
	}
	
	

	public Kysely(String nimi, User user) {
		super();
		this.nimi = nimi;
		this.user = user;
	}



	public Long getKyselyId() {
		return kyselyId;
	}

	public void setKyselyId(Long kyselyId) {
		this.kyselyId = kyselyId;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}
	
	
	
	
	


}
