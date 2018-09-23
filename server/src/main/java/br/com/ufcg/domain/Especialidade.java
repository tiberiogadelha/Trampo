package br.com.ufcg.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAB_ESPECIALIDADE")
public class Especialidade {
	
	@Id
	@GeneratedValue
	@Column(name = "ID_ESPECIALIDADE")
	private Long id;
	
	@Column(name = "TX_NOME", nullable = false)
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
