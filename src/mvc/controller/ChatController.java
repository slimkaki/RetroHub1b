package mvc.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.Message;
import mvc.model.Subject;
import mvc.model.SubjectDAO;
import mvc.model.User;
import mvc.model.UserDAO;

@Controller
public class ChatController {
	
	@RequestMapping("/a")
	public String execute() {
		return "chat";
	}
	
	@RequestMapping(value = "criaConversa", method = RequestMethod.POST)
	public String criaConversa(String subject) {
		Subject newSub = new Subject();
		newSub.setSubject(subject);
		newSub.setURL(subject);
		
		SubjectDAO dao = new SubjectDAO();
		dao.addSubject(newSub);
		
		return "main";
	}
	
	@RequestMapping("entraConversa")
	public String entraConversa(String myURL, HttpSession ses) {
		ses.setAttribute("chatURL", myURL);
		return "chat";
	}
	
	@RequestMapping(value = "getImage", method = RequestMethod.GET)
	public void showImage(@RequestParam("login") String login, HttpServletResponse response,HttpServletRequest request)
					throws ServletException, IOException {
		UserDAO dao = new UserDAO();
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(dao.buscaFoto(login));
		response.getOutputStream().close();
	}
	
	@RequestMapping("enviaMsg")
	public String enviaMsg(String msg, String myURL, HttpSession ses) {
		myURL = myURL.replace("/", "");
		SubjectDAO subDAO = new SubjectDAO();
		UserDAO userDAO = new UserDAO();
		Message message = new Message();
		User usuario = new User();
		String name = (String) ses.getAttribute("usuarioLogado");
		usuario.setName(name);
		int id = userDAO.getUserId(usuario);
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		message.setMsg(msg);
		message.setTime(ts);
		message.setIdUser(id);
		subDAO.addMessage(message, myURL);
		return "chat";
	}
	
	@RequestMapping("backToMenu")
	public String backToMenu() {
		return "main";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession ses) {
		ses.invalidate();
		return "login";
	}


}
