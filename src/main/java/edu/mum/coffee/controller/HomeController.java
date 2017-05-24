package edu.mum.coffee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@GetMapping({"/index", "/home"})
	public String homePage() {
		return "home";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
	 @RequestMapping(value = "/login", method = RequestMethod.GET)
	    public String login(Model model, Error error, String logout) {
		
	      //  if (error != null)
	        //    model.addAttribute("error", "Your username and password is invalid.");

	        if (logout != null)
	            model.addAttribute("message", "You have been logged out successfully.");
System.out.println("hellooooooooooooooooooo");
	        return "login";
	    }
	 @GetMapping({"/registration"})
		public String registerPage() {
			return "home";
		}
}
