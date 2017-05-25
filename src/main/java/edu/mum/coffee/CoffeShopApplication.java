package edu.mum.coffee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@SpringBootApplication
public class CoffeShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeShopApplication.class, args);
	}
	@Bean
	public ProductService pService(){
		return new ProductService();
	}
	@Bean
	public PersonService perService(){
		return new PersonService();
	}
	@Bean
	public OrderService oService(){
		return new OrderService();
	}
}