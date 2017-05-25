package edu.mum.coffee.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.coffee.service.PersonService;

@Controller
public class HomeController {
	@Autowired
	private PersonService perService;
	@GetMapping({"/index", "/home","/"})
	public String homePage() {
		return "redirect:/products";
	}
	@GetMapping({ "/admin"})
	public String adminPage(Model model,Principal p,HttpServletRequest request) {
		
		//model.addAttribute("person", perService.findByEmail(p.getName()).get(0));
		request.getSession().setAttribute("user", perService.findByEmail(p.getName()).get(0));
		return "admin";
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
	        return "redirect:/products";
	    }
	 @GetMapping({"/registration"})
		public String registerPage() {
			return "home";
		}
}
