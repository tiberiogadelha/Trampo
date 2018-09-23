package br.com.ufcg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufcg.models.Usuario;
import br.com.ufcg.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario getByLogin(String login) throws Exception {
		Optional<Usuario> userToFind = usuarioRepository.findByLogin(login);
		
		if(!userToFind.isPresent()) {
			throw new Exception("Usuario nao encontrado");
		}
		
		return userToFind.get();
	}

	public boolean checkUser(String login, String senha) {
		Usuario userToCheck = usuarioRepository.findByLoginAndSenha(login, senha);
		
		if(userToCheck == null) {
			return false;
		}
		
		return true;
	}

	
	
	
}
