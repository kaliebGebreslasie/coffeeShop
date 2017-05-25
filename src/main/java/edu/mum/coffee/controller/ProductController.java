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


import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.ProductService;
//import scala.annotation.meta.setter;

@Controller
public class ProductController {
@Autowired
private ProductService pService;
@Autowired
private OrderService oService;
	@GetMapping(value="/products")
	public String listProducts(Model model) {
		model.addAttribute("products", pService.getAllProduct());
		return "products";
	}

	@GetMapping(value="/order")
	public String displayOrder(Model model) {
		model.addAttribute("products", pService.getAllProduct());
		//model.addAttribute(""))
		return "order";
	}
	@GetMapping(value="/addOrder")
	
	public   String addOrder(Principal p,HttpServletRequest request,@RequestParam("selProduct") int productId,@RequestParam("quantity") int quantity,Model model) {
		//Map<Integer,Integer> list=new HashMap<>();
		List<Orderline>  list;
		Orderline ol=new Orderline();
		ol.setProduct(pService.getProduct(productId));
		ol.setQuantity(quantity);
		if(request.getSession().getAttribute("list")==null){
		list=new ArrayList<>();
			
			list.add(ol);
		}
		else{
		list=(List)request.getSession().getAttribute("list");
		
		list.add(ol);
		
		}
		request.getSession().setAttribute("list", list);
		model.addAttribute("user",p.getName());
	//	list.put(pService.getProduct(Integer.valueOf(productId)), quantity);
	//	model.addAttribute("list", list);
		//model.addAttribute(""))
		return "forward:/order" ;
	}
	@PostMapping(value="/order")
	public String order(HttpServletRequest request,Model model,Principal p) {
		//Map<Integer,Integer> list=new HashMap<>();
		List<Orderline>  list=(List)request.getSession().getAttribute("list");
		Order o=new Order();
		for(Orderline ol:list)
		o.addOrderLine(ol);
		oService.save(o);
	
		model.addAttribute("orderList",list);
		
//oService.
		//model.addAttribute(""))
		return "orderDetails";
	}
	
	@RequestMapping(value="/editProducts", method=RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("products", pService.getAllProduct());
		return "editProducts";
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public String get(@PathVariable int id, Model model) {
		model.addAttribute("product", pService.getProduct(id));
		model.addAttribute("productType",ProductType.values());
		
		return "productDetails";
	}

	@RequestMapping(value="/products/{id}", method=RequestMethod.POST)
	public String update(Product product, @PathVariable int id) {
		System.out.println("iddddddddddddddd "+product.getId());
		Product p= pService.getProduct(id);
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setProductName(product.getProductName());
		p.setProductType(product.getProductType());
		
		pService.save(p);
		
		return "redirect:/editProducts";
	}
	@RequestMapping(value="/del/{id}", method=RequestMethod.GET)
	public String del(@PathVariable int id, Model model) {
	//	model.addAttribute("product", pService.getProduct(id));
		pService.delete(pService.getProduct(id));
		return "redirect:/editProducts";
		
	}


	@RequestMapping(value="/newProduct", method=RequestMethod.GET)
	public String newProductt(Model model) {
	//	model.addAttribute("product", pService.getProduct(id));
		model.addAttribute("productType",ProductType.values());
		return "productDetails";
	}
	
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String addProduct(Product product) {
		System.out.println("iddddddddddddddd "+product.getId());
		
		pService.save(product);
		
		return "redirect:/editProducts";
	}
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public String listOrders(Model model) {
	//	System.out.println("iddddddddddddddd "+product.getId());
		
		model.addAttribute("orders", oService.findAll());
		
		return "orders";
	}
}
