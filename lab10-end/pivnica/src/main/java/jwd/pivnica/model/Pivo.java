package jwd.pivnica.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Pivo {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column(nullable=false,unique=true)
	private String naziv;
	@Column
	private String vrsta;
	@Column(nullable=false)
	private Float procenatAlkohola;
	@Column
	private Float ibu;
	@Column(nullable=false)
	private Integer kolicina;
	@ManyToOne(fetch=FetchType.EAGER)
	private Pivara pivara;
	@OneToMany(mappedBy="pivo",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<Kupovina> kupovine = new ArrayList<>();
	
	public Pivo() {
		super();
	}
	public Pivo(Long id, String naziv, String vrsta, Float procenatAlkohola, Float ibu, Integer kolicina) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.vrsta = vrsta;
		this.procenatAlkohola = procenatAlkohola;
		this.ibu = ibu;
		this.kolicina = kolicina;
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
	public String getVrsta() {
		return vrsta;
	}
	public void setVrsta(String vrsta) {
		this.vrsta = vrsta;
	}
	public Float getProcenatAlkohola() {
		return procenatAlkohola;
	}
	public void setProcenatAlkohola(Float procenatAlkohola) {
		this.procenatAlkohola = procenatAlkohola;
	}
	public Float getIbu() {
		return ibu;
	}
	public void setIbu(Float ibu) {
		this.ibu = ibu;
	}
	public Integer getKolicina() {
		return kolicina;
	}
	public void setKolicina(Integer kolicina) {
		this.kolicina = kolicina;
	}
	public Pivara getPivara() {
		return pivara;
	}
	public void setPivara(Pivara pivara) {
		this.pivara = pivara;
		if(pivara!=null && !pivara.getPiva().contains(this)){
			pivara.getPiva().add(this);
		}
	}
	public List<Kupovina> getKupovine() {
		return kupovine;
	}
	public void setKupovine(List<Kupovina> kupovine) {
		this.kupovine = kupovine;
	}
	public void addKupovina(Kupovina kupovina){
		this.kupovine.add(kupovina);
		
		if(!this.equals(kupovina.getPivo())){
			kupovina.setPivo(this);
		}
	}
}
