package edu.mum.coffee.restclient;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.client.RestTemplate;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;

public class RestClient {
	public static final String SERVER_URI = "http://172.17.3.120:8080/";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
System.out.println("hello world");
getAllProducts();
//getProduct();
//getAllPersons();
//getPerson();
getAllOrders();
//getOrder();
//createProduct();
//updateProduct();
//createPerson();
//updatePerson();
	}
	private static void getAllProducts() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Product> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> prods = restTemplate.getForObject(SERVER_URI+"products/", List.class);
	//	System.out.println(prods);
		for(LinkedHashMap map : prods){
			System.out.println("ID="+map.get("id")+",Product Name="+map.get("productName")+",Description="+map.get("description"));;
		}
	}
	
	private static void getProduct(){
		RestTemplate restTemplate = new RestTemplate();
		Product prod = restTemplate.getForObject(SERVER_URI+"products/177", Product.class);
		printProductData(prod);
	}
	
	private static void createProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product prod = new Product();
		prod.setProductName("Milk Shake");
		prod.setDescription("Milk and Bannana");
		prod.setPrice(10.0);
		prod.setProductType(ProductType.DINNER);
		Product response = restTemplate.postForObject(SERVER_URI+"products/", prod, Product.class);
		printProductData(response);
	}
	
	private static void deleteProduct(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(SERVER_URI+"products/delete/176");
		
	}
	
	private static void updateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product prod = new Product();
		prod.setProductName("Milk Shake");
		prod.setDescription("Milk,Bannana and Mango");
		prod.setPrice(16);
		prod.setProductType(ProductType.DINNER);
		System.out.println(prod.getId()+"iddddddd");
		restTemplate.put(SERVER_URI+"products/update/179", prod);
	//	Product response = restTemplate.postForObject(SERVER_URI+"update/product/1", prod, Product.class);
		//printProductData(response);
	}
	
	private static void getAllPersons() {
		RestTemplate restTemplate = new RestTemplate();
		
		List<LinkedHashMap> persons = restTemplate.getForObject(SERVER_URI+"persons/", List.class);
	//	System.out.println(persons);
		for(LinkedHashMap map : persons){
			System.out.println("ID="+map.get("id")+",First Name="+map.get("firstName")+",Last Name="+map.get("lastName")+",Email="+map.get("email")+",Address="+map.get("address")+",Phone="+map.get("phone"));;
		}
	}
	private static void getPerson(){
		RestTemplate restTemplate = new RestTemplate();
		Person pers = restTemplate.getForObject(SERVER_URI+"persons/29", Person.class);
		printPersonData(pers);
	}
	private static void createPerson() {
		RestTemplate restTemplate = new RestTemplate();
		Person pers = new Person();
		pers.setFirstName("Gerie");
		pers.setLastName("Abrha");
		pers.setPhone("22222");
		Address addr=new Address();
				addr.setCity("Fairfied");addr.setCountry("USA");addr.setState("IA");addr.setZipcode("52577");
		
		pers.setAddress(addr);
		pers.setEnable(true);
		pers.setEmail("kalebgebre@gmail.com");
		Person response = restTemplate.postForObject(SERVER_URI+"persons/", pers, Person.class);
		printPersonData(response);
	}
	
	private static void updatePerson() {
		RestTemplate restTemplate = new RestTemplate();
		Person pers = new Person();
		pers.setFirstName("Adonai");
		pers.setLastName("Solomon");
		pers.setPhone("22222111");
		Address addr=new Address();
		
		
		
		
				addr.setCity("Fairfied");addr.setCountry("USA");addr.setState("IA");addr.setZipcode("52577");addr.setCountry("USA");
		
		pers.setAddress(addr);
		pers.setEnable(true);
		pers.setEmail("adSol@gmail.com");
		restTemplate.put(SERVER_URI+"persons/update/30", pers);
//		Person response = restTemplate.postForObject(SERVER_URI+"persons/", pers, Person.class);
//		printPersonData(response);
	}
	
	private static void getAllOrders() {
		RestTemplate restTemplate = new RestTemplate();
		
		List<LinkedHashMap> orders = restTemplate.getForObject(SERVER_URI+"orders/", List.class);
	//	System.out.println(persons);
		for(LinkedHashMap map : orders){
			System.out.println("ID="+map.get("id")+",Order Date="+map.get("orderDate")+",Product="+map.get("orderLines"));
		}
	}
	private static void getOrder(){
		RestTemplate restTemplate = new RestTemplate();
		Order order = restTemplate.getForObject(SERVER_URI+"orders/27", Order.class);
		printOrderData(order);
	}
	
	private static void createOrder() {
		RestTemplate restTemplate = new RestTemplate();
		Person pers = restTemplate.getForObject(SERVER_URI+"persons/26", Person.class);
		
		Product prod = restTemplate.getForObject(SERVER_URI+"products/178", Product.class);

		
		Orderline oline=new Orderline();
	//	oline.setOrder(order);
		oline.setProduct(prod);
		oline.setQuantity(20);
		
		Order order = new Order();
		order.setOrderDate(new Date());
		order.setPerson(pers);
		order.addOrderLine(oline);
		Order response = restTemplate.postForObject(SERVER_URI+"orders/", order, Order.class);
		printOrderData(response);
	}
	
	public static void printProductData(Product prod){
		System.out.println("ID="+prod.getId()+",Product Name="+prod.getProductName()+",Description="+prod.getDescription()+",Price="+prod.getPrice()+",Product Type="+prod.getProductType());
	}
	
	public static void printPersonData(Person pers){
		System.out.println("ID="+pers.getId()+",First Name="+pers.getFirstName()+",Last Name="+pers.getLastName()+",Email="+pers.getEmail()+",City="+pers.getAddress().getCity()+",Phone="+pers.getPhone());
	}
	public static void printOrderData(Order order){
		System.out.println("ID="+order.getId()+",Order Date="+order.getOrderDate()+",Person Name="+order.getPerson().getFirstName());
		for(Orderline o: order.getOrderLines()){
			System.out.println("Product"+o.getProduct().getProductName()+" Quantity"+ o.getQuantity());
		}
	}
	
}
