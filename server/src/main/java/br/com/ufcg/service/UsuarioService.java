package br.com.ufcg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.enums.TipoUsuario;
import br.com.ufcg.repository.EspecialidadeRepository;
import br.com.ufcg.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private static final int TAMANHO_MINIMO_NOME = 8;
	private static final int TAMANHO_MINIMO_SENHA = 8;
	private static final int TAMANHO_MINIMO_LOGIN = 4;
	private static final String ESPACO_EM_BRANCO = " ";
	private static final String VAZIO = "";
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	EspecialidadeRepository especialidadeRepository;
	
	public Usuario getByLogin(String login) throws Exception {
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		if (usuario == null) {
			throw new Exception("Usuario nao encontrado");
		}
		
		return usuario;
	}

	public boolean checkUser(String login, String senha) {
		Usuario userToCheck = usuarioRepository.findByLoginAndSenha(login, senha);
		
		return (userToCheck != null);
	}
	
	public Usuario criarUsuario(Usuario usuario) throws Exception {
		if(validarUsuario(usuario)) {
			return usuarioRepository.save(usuario);
		} 
		
		return null;
		
	}
	
	private boolean usuarioEhUnico(Usuario usuario) throws Exception {
		Usuario foundByLogin = usuarioRepository.findByLogin(usuario.getLogin());
		Usuario foundByEmail = usuarioRepository.findByEmail(usuario.getEmail());
		
		if((foundByLogin == null) & (foundByEmail == null)) {
			return true;
		}
		throw new Exception("Email e/ou login ja estao sendo usandos. Tente outros, por favor.");
	}

	public boolean validarUsuario(Usuario usuario) throws Exception {
		boolean senhaComMaisDe8Digitos = validarTamanhoDaSenha(usuario.getSenha());
		boolean loginValido = validarLogin(usuario.getLogin());
		boolean nomeValido = validarNome(usuario.getNomeCompleto());
		boolean usuarioEhUnico = usuarioEhUnico(usuario);
		
		return senhaComMaisDe8Digitos && loginValido && nomeValido && usuarioEhUnico;
	}
	
	private boolean validarNome(String nomeCompleto) throws Exception {
		int tamanho = nomeCompleto.length();
		
		if(tamanho >= TAMANHO_MINIMO_NOME) {
			return true;
		}
		throw new Exception("O nome completo deve ter no minimo 8 digitos");
	}

	private boolean validarLogin(String login) throws Exception  {
		int tamanho = login.length();
		boolean loginComEspaco = login.contains(" ");
		
		if(tamanho >= TAMANHO_MINIMO_LOGIN && !loginComEspaco) {
			return true;
		}
		
		throw new Exception("O login deve ter no minimo 4 digitos e nao pode conter espaco");
	}

	private boolean validarTamanhoDaSenha(String senha) throws Exception {
		int tamanho = senha.length();
		if(tamanho >= TAMANHO_MINIMO_SENHA) {
			return true;
		}
		
		throw new Exception("A senha deve ter no minimo 8 digitos");
	}
	

	public List<Usuario> getClientes() {
		Iterable<Usuario> listaUsuarios = usuarioRepository.findAll();
		ArrayList<Usuario> clientes = new ArrayList<Usuario>();
		
		for(Usuario usuario : listaUsuarios){
			if(usuario.getTipo() == TipoUsuario.CLIENTE) {
				clientes.add(usuario);
			}
		}
		
		return clientes;
	}
	
	public List<Usuario> getFornecedores() {
		Iterable<Usuario> listaUsuarios = usuarioRepository.findAll();
		ArrayList<Usuario> fornecedores = new ArrayList<Usuario>();
		
		for(Usuario usuario : listaUsuarios){
			if(usuario.getTipo() == TipoUsuario.FORNECEDOR) {
				fornecedores.add(usuario);
			}
		}
		
		return fornecedores;
	}
	
	private boolean validarEspecialidade(Especialidade especialidade) throws Exception {
		String nome = especialidade.getNome();
		boolean ehUnica = especialidadeEhUnica(especialidade);
		
		if(!nome.equals(ESPACO_EM_BRANCO) && !nome.equals(VAZIO)  && ehUnica) {
			return true;
		}
		
		throw new Exception("Insira uma especialidade valida");
	}
	
	public boolean especialidadeEhUnica(Especialidade especialidade) throws Exception {
		Especialidade esp = especialidadeRepository.findByNome(especialidade.getNome());
		
		if(esp == null) {
			return true;
		}
		
		throw new Exception("Ja existe uma especialidade com esse nome!");
	}
	public Especialidade criarEspecialidade(Especialidade especialidade) throws Exception {
		if(validarEspecialidade(especialidade)) {
			return especialidadeRepository.save(especialidade);
		}
		
		return null;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidadeRepository.findAll();
	}
}
