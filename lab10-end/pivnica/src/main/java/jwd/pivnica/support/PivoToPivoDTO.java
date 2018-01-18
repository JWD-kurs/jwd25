package jwd.pivnica.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.pivnica.model.Pivo;
import jwd.pivnica.web.dto.PivoDTO;

@Component
public class PivoToPivoDTO 
	implements Converter<Pivo, PivoDTO> {

	@Override
	public PivoDTO convert(Pivo source) {
		PivoDTO dto = new PivoDTO();
		
		dto.setId(source.getId());
		dto.setNaziv(source.getNaziv());
		dto.setVrsta(source.getVrsta());
		dto.setProcenatAlkohola(source.getProcenatAlkohola());
		dto.setIbu(source.getIbu());
		dto.setKolicina(source.getKolicina());
		
		if(source.getPivara() != null) {
			dto.setPivaraId(source.getPivara().getId());
			dto.setPivaraNaziv(source.getPivara().getNaziv());
		}
		
		return dto;
	}
	
	public List<PivoDTO> convert(List<Pivo> piva){
		List<PivoDTO> ret = new ArrayList<>();
		
		for(Pivo p : piva){
			ret.add(convert(p));
		}
		
		return ret;
	}

}
