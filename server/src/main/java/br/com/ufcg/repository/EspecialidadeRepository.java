package br.com.ufcg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufcg.domain.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, String> {
	
	Especialidade findById(Long id);
	
	Especialidade findByNome(String nome);
}
