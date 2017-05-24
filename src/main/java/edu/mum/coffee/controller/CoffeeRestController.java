package edu.mum.coffee.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.ProductService;

@RestController
@RequestMapping("/products")
public class CoffeeRestController {
	@Autowired
	private ProductService pService;
	@RequestMapping("")
	public Collection<Product> getProducts(){
		return pService.getAllProduct();
	}
//@RequestMapping("")
}
