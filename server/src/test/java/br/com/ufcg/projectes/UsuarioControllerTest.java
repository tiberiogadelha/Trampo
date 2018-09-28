package br.com.ufcg.projectes;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ufcg.controller.UsuarioController;
import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.enums.TipoUsuario;
import br.com.ufcg.repository.EspecialidadeRepository;
import br.com.ufcg.repository.UsuarioRepository;
import br.com.ufcg.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioController uc;
	
	@Autowired
	UsuarioService us;
	
	@Autowired
	EspecialidadeRepository especialidadeRepository;

	@Test
	@Transactional
	public void testCriarClienteValido() {
		Cliente cliente1 = new Cliente("Tiberio Gadelha M", "tiberiogadelha", "/imgs/foto.png", "tiberio.gomes@ccc.ufcg.edu.br", "123456789");
		try {
			uc.cadastrarCliente(cliente1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Usuario us1 = null;
		
		try {
			us1 = us.getByLogin(cliente1.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertEquals("Tiberio Gadelha M", us1.getNomeCompleto());
		assertEquals("tiberiogadelha", us1.getLogin());
		assertEquals("tiberio.gomes@ccc.ufcg.edu.br", us1.getEmail());
		assertEquals(TipoUsuario.CLIENTE, us1.getTipo());
		
		
		Cliente cliente2 = new Cliente("Yuri Kelvin", "yurikelvin", "/imgs/foto.png", "yuri.kelvin@ccc.ufcg.edu.br", "123456789");
		
		try {
			uc.cadastrarCliente(cliente2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testCriarClienteInValido() {
		
		// Testa cliente com login de tamanho inferior a 4.
		Cliente cliente1 = new Cliente("Gustavo Victor", "gu", "/imgs/foto.png", "gustavo.victor@ccc.ufcg.edu.br", "123456789");
		try {
			uc.cadastrarCliente(cliente1);
		} catch (Exception e) {
			assertEquals("O login deve ter no minimo 4 digitos e nao pode conter espaco", e.getMessage());
		}
		
		// Testa cliente com nome de tamanho inferior a 8
		Cliente cliente2 = new Cliente("Joao", "joaohenrique", "/imgs/foto.png", "joao.henrique@ccc.ufcg.edu.br", "123456789");
		try {
			uc.cadastrarCliente(cliente2);
		} catch (Exception e) {
			assertEquals("O nome completo deve ter no minimo 8 digitos", e.getMessage());
		}
		
		// Testa cliente com senha de tamanho inferior a 8
		Cliente cliente3 = new Cliente("Emanuel Brito ", "emaulbrito", "/imgs/foto.png", "emanuel.brito@ccc.ufcg.edu.br", "1289");
		try {
			uc.cadastrarCliente(cliente3);
		} catch (Exception e) {
			assertEquals("A senha deve ter no minimo 8 digitos", e.getMessage());
		}
		
		// Testa cadastrar dois clientes com mesmo email e senha
		Cliente cliente4 = new Cliente("Tiberio Gadelha M", "tiberiogadelha", "/imgs/foto.png", "tiberio.gomes@ccc.ufcg.edu.br", "123456789");
		try {
			uc.cadastrarCliente(cliente4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			uc.cadastrarCliente(cliente4);
		} catch (Exception e) {
			assertEquals("Email e/ou login ja estao sendo usandos. Tente outros, por favor.", e.getMessage());
		}
		
	}
	/*
	@Test
	public void testCriarFornecedor() {
		fail("Not yet implemented");
	}
	*/
}
