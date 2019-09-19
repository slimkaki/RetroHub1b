package mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ChatController {
	
	@RequestMapping("/")
	public String execute() {
		return "chat";
	}

}
