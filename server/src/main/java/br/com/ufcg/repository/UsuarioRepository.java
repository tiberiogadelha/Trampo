package br.com.ufcg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufcg.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {

	Usuario findByLoginAndPassword(String login,String usuario);
}
