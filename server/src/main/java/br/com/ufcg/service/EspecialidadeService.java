package br.com.ufcg.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufcg.domain.Especialidade;
import br.com.ufcg.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	private static final String ESPACO_EM_BRANCO = " ";
	private static final String VAZIO = "";
	
	@Autowired
	EspecialidadeRepository especialidadeRepository;
	
	private boolean validarEspecialidade(Especialidade especialidade) throws Exception {
		String nome = especialidade.getNome();
		boolean ehUnica = especialidadeEhUnica(especialidade);
		
		if(!nome.equals(ESPACO_EM_BRANCO) && !nome.equals(VAZIO)  && ehUnica) {
			return true;
		}
		
		throw new Exception("Insira uma especialidade valida");
	}
	
	private boolean especialidadeEhUnica(Especialidade especialidade) throws Exception {
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
	
	public List<String> getNomesEspecialidades() {
		Iterable<Especialidade> especialidades = getEspecialidades();
		List<String> nomes = new ArrayList<>();
		
		for(Especialidade especialidade: especialidades) {
			nomes.add(especialidade.getNome());
		}
		
		return nomes;
	}
}
