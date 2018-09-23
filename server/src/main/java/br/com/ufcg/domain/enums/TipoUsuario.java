package br.com.ufcg.domain.enums;

public enum TipoUsuario {
	
	CLIENTE("Cliente"),
	FORNECEDOR("Fornecedor");
	
	private final String tipo;
	
	TipoUsuario(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
