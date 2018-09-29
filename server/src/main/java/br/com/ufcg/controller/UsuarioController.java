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

import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping(value = "/api/cliente", produces="application/json")
	public @ResponseBody  List<Usuario> listaClientes(){
		
		return usuarioService.getClientes();
	}
	
	@GetMapping(value = "/api/fornecedor", produces="application/json")
	public @ResponseBody  List<Usuario> listaFornecedores(){
		return usuarioService.getFornecedores();
	}
	
	@RequestMapping(value = "api/cliente", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastrarCliente(@RequestBody Cliente cliente) throws Exception {
		Usuario retorno = usuarioService.criarUsuario(cliente);
	    	
	    if(retorno != null) {
	    	return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	    } 
	    
	    return new ResponseEntity<>(retorno, HttpStatus.EXPECTATION_FAILED);
	}
	    
	@RequestMapping(value = "api/fornecedor", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) throws Exception {
		Usuario retorno = usuarioService.criarUsuario(fornecedor);
		
	    if(retorno != null) {
	    	return new ResponseEntity<>(retorno, HttpStatus.CREATED);
	    } 
	    
	    return new ResponseEntity<>(retorno, HttpStatus.EXPECTATION_FAILED);
	}


}
