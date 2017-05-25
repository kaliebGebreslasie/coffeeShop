package edu.mum.coffee.restclient;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@RestController
@RequestMapping("/api/orders")
public class RestOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/")
	public List<Order> getAll(){
		return orderService.findAll();
	}
	
	@GetMapping(value="/{id}")
	public Order getOrder(@PathVariable int id){
		return orderService.findById(id);
	}
	
	@GetMapping(value="/person/{id}")
	public List<Order> getByPerson(@PathVariable long id){
		Person person = personService.findById(id);
		return orderService.findByPerson(person);
	}
	
	@GetMapping(value="/product/{id}")
	public List<Order> getByProduct(@PathVariable int id){
		Product product = productService.getProduct(id);
		return orderService.findByProduct(product);
	}
	
	@GetMapping(value="/date/{minDate}/{maxDate}")
	public List<Order> getByDate(@PathVariable Date minDate, @PathVariable Date maxDate){
		return orderService.findByDate(minDate, maxDate);
	}
	
	@PostMapping(value="/")
	public Order saveOrder(@RequestBody Order order){
		return orderService.save(order);
	}
	
	@PutMapping(value="/update/{id}")
	public Order updateOrder(@PathVariable int id, @RequestBody Order order){
		Order order2 = orderService.findById(id);
		order2.setOrderDate(order.getOrderDate());
		order2.clearOrderLines();
		for(Orderline orderline : order.getOrderLines())
			order2.addOrderLine(orderline);
		order2.setPerson(order.getPerson());
		return orderService.save(order2);
	}
	
	@PostMapping(value="/delete")
	public void deleteOrder(@RequestBody Order order){
		orderService.delete(order);
	}
}
