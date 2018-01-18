package jwd.pivnica.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.pivnica.model.Kupovina;
import jwd.pivnica.model.Pivo;
import jwd.pivnica.service.KupovinaService;
import jwd.pivnica.service.PivoService;
import jwd.pivnica.support.KupovinaToKupovinaDTO;
import jwd.pivnica.support.PivoDTOToPivo;
import jwd.pivnica.support.PivoToPivoDTO;
import jwd.pivnica.web.dto.KupovinaDTO;
import jwd.pivnica.web.dto.PivoDTO;

@RestController
@RequestMapping("/api/piva")
public class ApiPivoController {
	@Autowired
	private PivoService pivoService;
	@Autowired
	private KupovinaService kupovinaService;
	@Autowired
	private PivoToPivoDTO toPivoDTO;
	@Autowired
	private KupovinaToKupovinaDTO toKupovinaDTO;
	@Autowired
	private PivoDTOToPivo toPivo;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PivoDTO>> get(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) Float minibu,
			@RequestParam(required=false) Float maxibu,
			@RequestParam(required=false) Long pivaraId,
			@RequestParam(defaultValue="0") int pageNum){
		
		Page<Pivo> piva;
		
		if(naziv != null || minibu != null || maxibu != null || pivaraId != null) {
			piva = pivoService.pretraga(naziv, minibu, maxibu, pivaraId, pageNum);
		}else{
			piva = pivoService.findAll(pageNum);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(piva.getTotalPages()) );
		return  new ResponseEntity<>(
				toPivoDTO.convert(piva.getContent()),
				headers,
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,
					value="/{id}")
	public ResponseEntity<PivoDTO> get(
			@PathVariable Long id){
		Pivo pivo = pivoService.findOne(id);
		
		if(pivo==null){
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(
				toPivoDTO.convert(pivo),
				HttpStatus.OK);	
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<PivoDTO> add(
			@RequestBody @Validated PivoDTO novoPivo){
		
		Pivo pivo = toPivo.convert(novoPivo); 
		pivoService.save(pivo);
		
		return new ResponseEntity<>(toPivoDTO.convert(pivo),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/kupovina")
	public ResponseEntity<KupovinaDTO> buy(@PathVariable Long id){
		
		Kupovina k = kupovinaService.buyABeer(id);
		
		if(k == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<>(toKupovinaDTO.convert(k), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			value="/{id}")
	public ResponseEntity<PivoDTO> edit(
			@PathVariable Long id,
			@RequestBody @Validated PivoDTO izmenjen){
		
		if(!id.equals(izmenjen.getId())){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Pivo pivo = toPivo.convert(izmenjen); 
		pivoService.save(pivo);
		
		return new ResponseEntity<>(toPivoDTO.convert(pivo),
				HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<PivoDTO> delete(@PathVariable Long id){
		
		Pivo pivo = pivoService.findOne(id);
		if(pivo != null) {
			pivoService.remove(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
