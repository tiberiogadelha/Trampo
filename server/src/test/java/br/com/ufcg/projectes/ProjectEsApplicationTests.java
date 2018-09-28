package br.com.ufcg.projectes;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
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
import br.com.ufcg.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectEsApplicationTests {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioController uc;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	@Transactional
	public void testRepository() {
		String imagemBase64 = "";
		
		usuarioRepository.save(new Cliente("Emanuel Brito", "emanuel.brito", imagemBase64, "emanuel.brito@ccc.ufcg.edu.br", "12345678"));
		usuarioRepository.save(new Cliente("Gustavo Cavalcante", "gustavo.cavalcante", imagemBase64, "gustavo.cavalcante@ccc.ufcg.edu.br", "12345678"));
		usuarioRepository.save(new Cliente("Natan Ribeiro", "natan.ribeiro", imagemBase64, "natan.ribeiro@ccc.ufcg.edu.br", "12345678"));
		usuarioRepository.save(new Cliente("Ronan Souza", "ronan.souza", imagemBase64, "ronan.souza@ccc.ufcg.edu.br", "12345678"));
		usuarioRepository.save(new Cliente("Tiberio Gomes", "tiberio.gomes", imagemBase64, "tiberio.gomes@ccc.ufcg.edu.br", "12345678"));
		
		List<Especialidade> listaEspecialidade = new ArrayList<>();
		listaEspecialidade.add(new Especialidade("Pintor"));
		listaEspecialidade.add(new Especialidade("Encanador"));
		listaEspecialidade.add(new Especialidade("Pedreiro"));
		
		usuarioRepository.save(new Fornecedor("Yuri Silva", "yuri.silva", imagemBase64, "yuri.silva@ccc.ufcg.edu.br", "12345678", listaEspecialidade));
		usuarioRepository.save(new Fornecedor("Oliveiros Neto", "oliveiros.neto", imagemBase64, "oliveirosnt@gmail.com", "12345678", listaEspecialidade));
		
		final List<Usuario> todosUsuarios = usuarioRepository.findAll();
		Assert.assertEquals(7, todosUsuarios.size());
		
		final Usuario usuario1 = usuarioRepository.findByLogin("emanuel.brito");
        Assert.assertEquals("Emanuel Brito", usuario1.getNomeCompleto());
        
        final Usuario usuario2 = usuarioRepository.findByLoginAndSenha("gustavo.cavalcante", "12345678");
        Assert.assertEquals("Gustavo Cavalcante", usuario2.getNomeCompleto());
	}
}