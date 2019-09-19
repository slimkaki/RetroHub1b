package mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class LoginController {
	
	@RequestMapping("/login")
	public String efetuaLogin() {
		return "login";
	}

	@RequestMapping("/cadastro")
	public String entraCadastro() {
		return "cadastro";
	}
}
