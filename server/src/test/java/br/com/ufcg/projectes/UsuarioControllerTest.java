package br.com.ufcg.projectes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ufcg.controller.UsuarioController;
import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.domain.Fornecedor;
import br.com.ufcg.domain.Usuario;
import br.com.ufcg.domain.enums.TipoUsuario;
import br.com.ufcg.repository.UsuarioRepository;
import br.com.ufcg.service.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioControllerTest {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioController usuarioController;

	@Autowired
	UsuarioService usuarioService;

	@Test
	@Transactional
	public void testCriarClienteValido() {
		Cliente cliente1 = new Cliente("Tiberio Gadelha M", "tiberiogadelha", "/imgs/foto.png",
				"tiberio.gomes@ccc.ufcg.edu.br", "123456789");
		try {
			usuarioController.cadastrarCliente(cliente1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Usuario us1 = null;

		try {
			us1 = usuarioService.getByLogin(cliente1.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertEquals("Tiberio Gadelha M", us1.getNomeCompleto());
		assertEquals("tiberiogadelha", us1.getLogin());
		assertEquals("tiberio.gomes@ccc.ufcg.edu.br", us1.getEmail());
		assertEquals(TipoUsuario.CLIENTE, us1.getTipo());

		Cliente cliente2 = new Cliente("Yuri Kelvin", "yurikelvin", "/imgs/foto.png", "yuri.kelvin@ccc.ufcg.edu.br",
				"123456789");

		try {
			usuarioController.cadastrarCliente(cliente2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Transactional
	public void testCriarClienteInValido() {

		// Testa cliente com login de tamanho inferior a 4.
		Cliente cliente1 = new Cliente("Gustavo Victor", "gu", "/imgs/foto.png", "gustavo.victor@ccc.ufcg.edu.br",
				"123456789");
		try {
			usuarioController.cadastrarCliente(cliente1);
		} catch (Exception e) {
			assertEquals("O login deve ter no minimo 4 digitos e nao pode conter espaco", e.getMessage());
		}

		// Testa cliente com nome de tamanho inferior a 8
		Cliente cliente2 = new Cliente("Joao", "joaohenrique", "/imgs/foto.png", "joao.henrique@ccc.ufcg.edu.br",
				"123456789");
		try {
			usuarioController.cadastrarCliente(cliente2);
		} catch (Exception e) {
			assertEquals("O nome completo deve ter no minimo 8 digitos", e.getMessage());
		}

		// Testa cliente com senha de tamanho inferior a 8
		Cliente cliente3 = new Cliente("Emanuel Brito ", "emaulbrito", "/imgs/foto.png",
				"emanuel.brito@ccc.ufcg.edu.br", "1289");
		try {
			usuarioController.cadastrarCliente(cliente3);
		} catch (Exception e) {
			assertEquals("A senha deve ter no minimo 8 digitos", e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testCriarClienteDuplicado() {
		// Testa cadastrar dois clientes com mesmo email e login
		Cliente cliente4 = new Cliente("Tiberio Gadelha M", "tiberiogomes", "/imgs/foto.png", "tiberio@ccc.ufcg.edu.br",
				"123456789");
		try {
			usuarioController.cadastrarCliente(cliente4);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			usuarioController.cadastrarCliente(cliente4);
		} catch (Exception e) {
			assertEquals("Email e/ou login já estão sendo usandos. Tente outros, por favor.", e.getMessage());
		}
	}

	@Test
	@Transactional
	public void testCriarFornecedorValido() {
		List<Especialidade> listaEspecialidade = new ArrayList<>();
		listaEspecialidade.add(new Especialidade("Pintor"));
		listaEspecialidade.add(new Especialidade("Encanador"));
		listaEspecialidade.add(new Especialidade("Pedreiro"));

		Fornecedor fornecedor1 = new Fornecedor("Carlos Alberto dos Anjos", "carlosaba", "/imgs/foto.png",
				"carlos.alberto1@gmail.com", "123456789", listaEspecialidade);
		try {
			usuarioController.cadastrarFornecedor(fornecedor1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Usuario foundByLogin = null;
		try {
			foundByLogin = usuarioService.getByLogin(fornecedor1.getLogin());
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(foundByLogin.getNomeCompleto().equals(fornecedor1.getNomeCompleto()));
		assertEquals(3, ((Fornecedor) foundByLogin).getListaEspecialidades().size());
	}
}
