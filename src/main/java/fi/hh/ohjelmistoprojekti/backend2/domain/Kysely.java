package fi.hh.ohjelmistoprojekti.backend2.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Kysely {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kyselyId;
	private String kysymys;
	

	public Kysely(Long kyselyId, String kysymys) {
		super();
		this.kyselyId = kyselyId;
		this.kysymys = kysymys;
		
	}
	
	public Kysely(String kysymys) {
		super();
		this.kysymys = kysymys;
		
		
	}
	
	public Kysely() {
		
	}
	
	
	public void setKyselyId(Long kyselyId) {
		this.kyselyId = kyselyId;
	}

	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}

	public Long getKyselyId() {
		return kyselyId;
	}

	public String getKysymys() {
		return kysymys;
	}

	@Override
	public String toString() {
		return "Kysely [kyselyId=" + kyselyId + ", kysymys=" + kysymys + "]";
	}


	
}
