package br.com.ufcg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.service.EspecialidadeService;

@RestController
public class EspecialidadeController {
	
	@Autowired
	EspecialidadeService especialidadeService;
	
	@RequestMapping(value = "api/especialidade", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Especialidade> cadastraEspecialidade(@RequestBody Especialidade especialidade) throws Exception {
		Especialidade retorno = especialidadeService.criarEspecialidade(especialidade);
	    	
	    if(retorno != null) {
	    	return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	    } 
	    
	    return new ResponseEntity<>(retorno, HttpStatus.EXPECTATION_FAILED);
	}
	
	@GetMapping(value = "/api/especialidade", produces="application/json")
	public @ResponseBody  List<Especialidade> listaEspecialidades(){
		return especialidadeService.getEspecialidades();
	}

}
