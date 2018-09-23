package br.com.ufcg.controller;

import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import br.com.ufcg.models.Usuario;
import br.com.ufcg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.Date;

@RestController
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value="/login", consumes= MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@RequestBody Usuario usuario) throws Exception {


        if(usuario.getLogin() == null || usuario.getSenha() == null) {
            return new ResponseEntity<>(new LoginResponse(""), HttpStatus.NOT_ACCEPTABLE);
        }

        Usuario usuAutenticado = usuarioService.getByLogin(usuario.getLogin());


        if(usuAutenticado == null) {
            return new ResponseEntity<>(new LoginResponse("Usuario nao existe"), HttpStatus.NOT_FOUND);
        }

        if(!usuario.getSenha().equals(usuAutenticado.getSenha())) {
            return new ResponseEntity<>(new LoginResponse("Senha incorreta"), HttpStatus.UNAUTHORIZED);
        }
        
        Integer HORAS = 3600 * 1000;
        String SECRET = "ProjetoES";

        String token = Jwts.builder()
                .setSubject(usuario.getLogin() + " " + usuario.getSenha())
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * HORAS))
                .compact();

        return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);
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