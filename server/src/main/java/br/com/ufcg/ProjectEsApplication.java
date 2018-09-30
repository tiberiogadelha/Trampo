package br.com.ufcg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.ufcg.jwt.TokenFilter;

@SpringBootApplication
public class ProjectEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEsApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean filtroJwt() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/usuarios/u/*");

		return frb;
	}
}
