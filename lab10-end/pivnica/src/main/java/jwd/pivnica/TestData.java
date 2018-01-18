package jwd.pivnica;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.pivnica.model.Pivara;
import jwd.pivnica.model.Pivo;
import jwd.pivnica.service.PivaraService;
import jwd.pivnica.service.PivoService;


@Component
public class TestData {
	@Autowired
	private PivaraService pivaraService;
	@Autowired
	private PivoService pivoService;

	@PostConstruct
	public void init() {
		
		Pivara p1 = new Pivara();
		p1.setNaziv("Apatinska");
		p1.setPIB("12121212");
		p1.setDrzava("Srbija");
		pivaraService.save(p1);
		
		Pivara p2 = new Pivara();
		p2.setNaziv("Celarevo");
		p2.setPIB("32132111");
		p2.setDrzava("Srbija");
		pivaraService.save(p2);
				
		Pivo pi1 = new Pivo();
		pi1.setNaziv("Jelen");
		pi1.setPivara(p1);
		pi1.setVrsta("svetlo");
		pi1.setIbu((float)11.0);
		pi1.setProcenatAlkohola((float)7.0);
		pi1.setKolicina(123);
		pivoService.save(pi1);
	}
}