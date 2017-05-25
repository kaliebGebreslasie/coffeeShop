package edu.mum.coffee.restclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class RestPersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(value="/")
	public List<Person> getPersons(){
		return personService.getAllPerson();
	}
	
	@GetMapping(value="/email/{email}")
	public List<Person> getByEmail(@PathVariable String email){
		return personService.findByEmail(email);
	}
	
	@GetMapping(value="/{id}")
	public Person getPerson(@PathVariable long id){
		return personService.findById(id);
	}
	
	@PostMapping(value="/")
	public Person savePerson(@RequestBody Person person){
		return personService.savePerson(person);
	}
	
	@PutMapping(value="/update/{id}")
	public Person updatePerson(@PathVariable long id, @RequestBody Person person){
		Person person2 = personService.findById(id);
		person2.setAddress(person.getAddress());
		person2.setEmail(person.getEmail());
		person2.setEnable(person.isEnable());
		person2.setFirstName(person.getFirstName());
		person2.setLastName(person.getLastName());
		person2.setPhone(person.getPhone());
		return personService.savePerson(person2);
	}
	
	@PostMapping(value="/delete/{id}")
	public void deletePerson(@PathVariable long id){
		personService.removePerson(personService.findById(id));
	}
}