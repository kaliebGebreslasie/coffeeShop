package edu.mum.coffee.controller;



import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
import java.util.*;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;
//import scala.annotation.meta.setter;

@Controller
public class PersonController {
@Autowired
private ProductService pService;
@Autowired
private OrderService oService;
@Autowired
private PersonService perService;
	@GetMapping(value="/persons")
	public String listPersons(Model model) {
		model.addAttribute("persons", perService.getAllPerson());
		return "persons";
	}
	
	@RequestMapping(value="/newPerson", method=RequestMethod.GET)
	public String newPerson(Model model,RedirectAttributes redirectAttrs,HttpServletRequest request) {
	//	model.addAttribute("product", pService.getProduct(id));
		//model.addAttribute("productType",ProductType.values());
		return "personDetails";
	}
	@RequestMapping(value="/persons", method=RequestMethod.POST)
	public String addPerson(Person person,Address address,RedirectAttributes redirectAttrs,HttpServletRequest request) {
		//System.out.println("iddddddddddddddd "+product.getId());
		person.setAddress(address);
//	System.out.println("city"+ address.getCity());
//	System.out.println("city"+ address.getCity());
		perService.savePerson(person);
		redirectAttrs.addFlashAttribute("user", (Person)request.getSession().getAttribute("user"));
		return "redirect:/persons";
	}
	
	@RequestMapping(value="/persons/{id}", method=RequestMethod.POST)
	public String updatePerson(Person person,Address address,@PathVariable long id,RedirectAttributes redirectAttrs,HttpServletRequest request) {
		person.setAddress(address);
		Person p= perService.findById(id);
		p.setFirstName(person.getFirstName());
		p.setLastName(person.getLastName());
		p.setEmail(person.getEmail());
		p.setPhone(person.getPhone());
		p.setAddress(person.getAddress());
		//System.out.println("iddddddddddddddd "+product.getId());
	//	person.setAddress(address);
		redirectAttrs.addFlashAttribute("user", (Person)request.getSession().getAttribute("user"));
		perService.savePerson(p);
		
		return "redirect:/admin";
	}
	
	@RequestMapping(value="/delPerson/{id}", method=RequestMethod.GET)
	public String delPerson(@PathVariable long id, Model model,RedirectAttributes redirectAttrs,HttpServletRequest request) {
	//	model.addAttribute("product", pService.getProduct(id));
		redirectAttrs.addFlashAttribute("user", (Person)request.getSession().getAttribute("user"));
		perService.removePerson(perService.findById(id));
		return "redirect:/persons";
		
	}
	@RequestMapping(value="/editProfile/{id}", method=RequestMethod.GET)
	public String editProfile(@PathVariable long id, Model model,RedirectAttributes redirectAttrs,HttpServletRequest request) {
	model.addAttribute("person", perService.findById(id));
	
		return "personDetails";
		
	}
}
