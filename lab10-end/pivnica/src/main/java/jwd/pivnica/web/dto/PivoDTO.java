package jwd.pivnica.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class PivoDTO {
	
	private Long id;
	@Size(max=40)
	private String naziv;
	private String vrsta;
	@Min(0)
	@Max(100)
	private Float procenatAlkohola;
	private Float ibu;
	@Min(0)
	private Integer kolicina;
	private Long pivaraId;
	private String pivaraNaziv;
	
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
		return this.ibu;
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
	public Long getPivaraId() {
		return pivaraId;
	}
	public void setPivaraId(Long pivaraId) {
		this.pivaraId = pivaraId;
	}
	public String getPivaraNaziv() {
		return pivaraNaziv;
	}
	public void setPivaraNaziv(String pivaraNaziv) {
		this.pivaraNaziv = pivaraNaziv;
	}
	
	
}
