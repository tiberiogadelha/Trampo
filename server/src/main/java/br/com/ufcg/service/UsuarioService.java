package br.com.ufcg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufcg.model.Usuario;
import br.com.ufcg.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario getByLogin(String login) throws Exception {
		Optional<Usuario> userToFind = usuarioRepository.findById(login);
		
		if(!userToFind.isPresent()) {
			throw new Exception("Usuario nao encontrado");
		}
		
		return userToFind.get();
	}

	public boolean checkUser(String login, String password) {
		Usuario userToCheck = usuarioRepository.findByLoginAndPassword(login, password);
		
		if(userToCheck == null) {
			return false;
		}
		
		return true;
	}

	
	
	
}
