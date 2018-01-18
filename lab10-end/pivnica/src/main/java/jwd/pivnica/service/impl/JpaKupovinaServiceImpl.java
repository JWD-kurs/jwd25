package jwd.pivnica.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.pivnica.model.Kupovina;
import jwd.pivnica.model.Pivo;
import jwd.pivnica.repository.KupovinaRepository;
import jwd.pivnica.repository.PivoRepository;
import jwd.pivnica.service.KupovinaService;

@Service
public class JpaKupovinaServiceImpl implements KupovinaService{
	
	@Autowired
	private KupovinaRepository kupovinaRepository;
	@Autowired
	private PivoRepository pivoRepository;
	
	@Override
	public Kupovina buyABeer(Long pivoId) {
		
		if(pivoId == null) {
			throw new IllegalArgumentException("Id of a beer cannot be null!");
		}
		
		Pivo pivo = pivoRepository.findOne(pivoId);
		if(pivo == null) {
			throw new IllegalArgumentException("There is no book with given id!");
		}
		
		if(pivo.getKolicina() > 0) {
			
			Kupovina kupovina = new Kupovina();
			kupovina.setPivo(pivo);
			
			pivo.setKolicina(pivo.getKolicina() - 1);
			
			kupovinaRepository.save(kupovina);
			pivoRepository.save(pivo);
			
			return kupovina;
		}
		
		return null;
		
	}
}
