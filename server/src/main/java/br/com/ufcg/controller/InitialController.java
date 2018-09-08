package br.com.ufcg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {
	
	@GetMapping("/helloworld")
	public String index() {
		return "Hello World!";
	}
}
