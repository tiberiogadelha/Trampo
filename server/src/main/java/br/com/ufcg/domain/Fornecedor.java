package br.com.ufcg.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value = "Fornecedor")
public class Fornecedor extends Usuario {
	
	@ManyToMany
	@JoinTable(name = "USUARIO_HAS_ESPECIALIDADE", 
			joinColumns = { @JoinColumn(name="USUARIO_ID") }, 
			inverseJoinColumns = { @JoinColumn(name = "ESPECIALIDADE_ID") })
	private List<Especialidade> listaEspecialidades;

	public List<Especialidade> getListaEspecialidades() {
		return listaEspecialidades;
	}

	public void setListaEspecialidades(List<Especialidade> listaEspecialidades) {
		this.listaEspecialidades = listaEspecialidades;
	}
}
