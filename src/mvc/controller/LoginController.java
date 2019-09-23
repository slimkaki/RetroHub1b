package mvc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.Subject;
import mvc.model.User;
import mvc.model.UserDAO;

@Controller
public class LoginController {

	@RequestMapping("/")
	public String execute() {
		//System.out.println("Lógica do MVC, entrou no /");
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
	public String redirectCadastro() { 
		return "cadastro";
	}

	@RequestMapping(value = "efetuaCadastro", method = RequestMethod.POST)
	public String efetuaCadastro(User user, String passwordConf, HttpSession ses) {
		UserDAO Udao = new UserDAO();
		
		if (!user.getPassword().contentEquals(passwordConf)) {
			ses.setAttribute("erro", "Senhas não batem!");
			return "cadastro";
		}

		if (Udao.checkIfUserExists(user)) {
			ses.setAttribute("erro", "Usuário existente");
			return "cadastro";	
		}
		
		try {
			Udao.addUser(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:login";
	}
	
	@RequestMapping("backToLogin")
	public String backToLogin() {
		return "login";
	}
}
