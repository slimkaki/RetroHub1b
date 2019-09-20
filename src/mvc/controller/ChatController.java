package mvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class ChatController {
	
	@RequestMapping("/a")
	public String execute() {
		return "chat";
	}

}
