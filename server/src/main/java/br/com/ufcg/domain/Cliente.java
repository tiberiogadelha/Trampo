package br.com.ufcg.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.ufcg.domain.enums.TipoUsuario;

@Entity
@DiscriminatorValue(value = "Cliente")
public class Cliente extends Usuario {

	public Cliente(String nomeCompleto, String login, String fotoPerfil, 
			String email, String senha) {
		super(nomeCompleto, login, fotoPerfil, email, senha, TipoUsuario.CLIENTE);
	}
	
	public Cliente() {
		super(null, null, null, null, null, TipoUsuario.CLIENTE);
	}
}
