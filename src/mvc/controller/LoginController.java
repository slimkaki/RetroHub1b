package mvc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import mvc.model.User;
import mvc.model.UserDAO;

public class LoginController {

	@RequestMapping("/")
	public String execute() {
		System.out.println("Lógica do MVC, entrou no /");
		return "login";
	}

	
	@RequestMapping("/login")
	public String LoginForm() {
		return "login";
	}

	@RequestMapping("efetuaLogin")
	public String efetuaLogin(User user, HttpSession ses) {
		if (new UserDAO().checkIfUserExists(user)) {
			ses.setAttribute("usuarioLogado", user.getName());
			return "main";
		}
		return "redirect:login";
	}

	@RequestMapping("/cadastro")
	public String entraCadastro(User user, String passwordConf) {
		
		UserDAO Udao = new UserDAO();
		
		if (user.getPassword() != passwordConf) {
			
			return "cadastro";
		}
		
		if (!Udao.checkIfUserExists(user)) {
			
			Udao.addUser(user);
			return "login";
		}
		
		return "cadastroAgain";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
