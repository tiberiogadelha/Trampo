package br.com.ufcg.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import br.com.ufcg.domain.enums.TipoUsuario;

@Entity
@DiscriminatorValue(value = "Fornecedor")
public class Fornecedor extends Usuario {
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_HAS_ESPECIALIDADE", 
			joinColumns = { @JoinColumn(name="USUARIO_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "ESPECIALIDADE_ID") })
	private List<Especialidade> listaEspecialidades;
	
	public Fornecedor(String nomeCompleto, String login, String fotoPerfil, 
			String email, String senha, List<Especialidade> listaEspecialidades) {
		super(nomeCompleto, login, fotoPerfil, email, senha, TipoUsuario.FORNECEDOR);
		this.listaEspecialidades = listaEspecialidades;
	}

	public Fornecedor() {
		super(null, null, null, null, null, TipoUsuario.FORNECEDOR);
	}

	public List<Especialidade> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidade> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}

	@Override
	public String toString() {
		return "Fornecedor " + super.getId() + ": "  + "nome - " + super.getNomeCompleto() + " | Especialidade(s) - " + listaEspecialidades.toString();
	}
	
	
}
