package br.com.ufcg.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import br.com.ufcg.service.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class TokenFilter extends GenericFilterBean {

    private UsuarioService usuarioService;
    
    // CONSTANTES NECESSÁRIAS AO CONTROLLER
    public static final String SECRET = "ProjetoES";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	if (usuarioService==null){
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            usuarioService = webApplicationContext.getBean(UsuarioService.class);
        }

        HttpServletRequest req = (HttpServletRequest) request;

        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Token inexistente ou inválido.");
        }

        String token = header.substring(7);

        // Verificar se o token é válido
        try {
            String tokenBody = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();

            validaToken(tokenBody, req.getRequestURI());

        } catch(SignatureException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido.");
        }

        chain.doFilter(request, response);
    }

    private void validaToken(String tokenBody, String requestURI) throws ServletException{
        String[] usuario = tokenBody.split(" ");
        String login = usuario[0];
        String password = usuario[1];

        if (!usuarioService.checkUser(login, password)) {
            throw new ServletException("Token com acesso bloqueado");
        }

        String[] paths = requestURI.split("/");
        String requestEmail = paths[3];
        if (!login.equals(requestEmail)) {
            throw new ServletException("Token com acesso bloqueado");
        }

    }
}