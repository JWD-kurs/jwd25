package jwd.pivnica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Pivara {
	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false,unique=true)
	private String naziv;
	@Column(unique=true)
	private String PIB;
	@Column
	private String drzava;
	@OneToMany(mappedBy="pivara",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Pivo> piva = new ArrayList<>();
	
	
	public Pivara() {
		super();
	}
	public Pivara(Long id, String naziv, String pIB, String drzava) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.PIB = pIB;
		this.drzava = drzava;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getPIB() {
		return PIB;
	}
	public void setPIB(String pIB) {
		PIB = pIB;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public List<Pivo> getPiva() {
		return piva;
	}
	public void setPiva(List<Pivo> piva) {
		this.piva = piva;
	}
	public void addPivo(Pivo pivo){
		this.piva.add(pivo);
		
		if(!this.equals(pivo.getPivara())){
			pivo.setPivara(this);
		}
	}
	
	
}
