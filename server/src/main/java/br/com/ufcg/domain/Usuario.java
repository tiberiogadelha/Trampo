package br.com.ufcg.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.com.ufcg.domain.enums.TipoUsuario;

@Entity
@Table(name = "TAB_USUARIO")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CD_TIPO", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
	
	@Id
	@GeneratedValue
	@Column(name = "ID_USUARIO")
	private Long id;
	
	@Column(name = "TX_NOME_COMPLETO")
	private String nomeCompleto;
	
	@Column(name = "TX_NOME_USUARIO")
	private String nomeUsuario;
	
	@Column(name = "CD_FOTO_PERFIL", nullable = false)
	private String fotoPerfil;
	
	@Column(name = "TX_EMAIL", nullable = false)
	private String email;
	
	@Column(name = "CD_TIPO", nullable = false, insertable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;
	
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public String getFotoPerfil() {
		return fotoPerfil;
	}
	
	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public TipoUsuario getTipo() {
		return tipo;
	}
}
