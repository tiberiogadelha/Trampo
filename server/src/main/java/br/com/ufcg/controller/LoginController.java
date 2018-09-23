package br.com.ufcg.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufcg.domain.Usuario;
import br.com.ufcg.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;
    
    // CONSTANTES NECESSÁRIAS AO CONTROLLER
    public static final String STRING_VAZIA = "";
    public static final String STRING_ESPACAMENTO = " ";
    public static final String USUARIO_NAO_EXISTENTE = "Usuario nao existe";
    public static final String SENHA_INCORRETA = "Senha incorreta";
    public static final String SECRET = "ProjetoES";
    public static final Integer HORAS = (3600 * 1000);
    public static final Integer HORAS_NO_DIA = 24;

    @RequestMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody Usuario usuario) throws Exception {


        if (usuario.getLogin() == null || usuario.getSenha() == null) {
            return new ResponseEntity<>(new LoginResponse(STRING_VAZIA), HttpStatus.NOT_ACCEPTABLE);
        }

        Usuario usuAutenticado = usuarioService.getByLogin(usuario.getLogin());

        if (usuAutenticado == null) {
            return new ResponseEntity<>(new LoginResponse(USUARIO_NAO_EXISTENTE), HttpStatus.NOT_FOUND);
        }

        if (!usuario.getSenha().equals(usuAutenticado.getSenha())) {
            return new ResponseEntity<>(new LoginResponse(SENHA_INCORRETA), HttpStatus.UNAUTHORIZED);
        }
        
        String token = Jwts.builder()
				            .setSubject(new StringBuilder(usuario.getLogin()).append(STRING_ESPACAMENTO).append(usuario.getSenha()).toString())
				            .signWith(SignatureAlgorithm.HS512, SECRET)
				            .setExpiration(new Date(System.currentTimeMillis() + HORAS_NO_DIA * HORAS))
				            .compact();

        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/usuario", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) throws Exception {
    	Usuario retorno = usuarioService.criarUsuario(usuario);
    	
    	return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    private class LoginResponse {
        String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }
}