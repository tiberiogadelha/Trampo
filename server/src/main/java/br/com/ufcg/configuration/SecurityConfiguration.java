package br.com.ufcg.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String[] PUBLIC_MATCHERS = {
		"/api/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// EXPLICITANDO PARA APLICAÇÃO AS CONFIGURAÇÕES DE CORS DEFINIDAS ABAIXO
		// E DESABILITANDO A PROTEÇÃO CSRF
		http.cors().and()
			.csrf().disable();
		
		// CONFIGURANDO QUAIS ENDPOINTS SÃO NECESSÁRIOS A AUTENTICAÇÃO OU NÃO
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		
		// ASSEGURAR QUE A APLICAÇÃO NÃO VAI CRIAR SESSÃO DE USUÁRIO
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		// PERMITINDO O ACESSO AOS ENDPOINTS DA APLICAÇÃO POR MÚLTIPLAS FONTES 
		// COM AS CONFIGURAÇÕES BÁSICAS
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
