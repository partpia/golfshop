package hh.swd20.golfshop.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.golfshop.domain.BrandRepository;
import hh.swd20.golfshop.domain.CategoryRepository;
import hh.swd20.golfshop.domain.Gender;
import hh.swd20.golfshop.domain.Product;
import hh.swd20.golfshop.domain.ProductRepository;
import hh.swd20.golfshop.domain.User;
import hh.swd20.golfshop.domain.UserRepository;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private CategoryRepository categoryRepositor;
	@Autowired
	UserRepository userRepository;

	// REST
	@GetMapping("/products")
	public @ResponseBody List<Product> productListRest() {
		return (List<Product>) productRepository.findAll(); 
	}
	
	// all products from the database
	@GetMapping("/productlist")
	public String getAllProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "productlist"; // TODO: add html
	}
	
	// create a new product, only logged in user or admin
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping("/newproduct")
	public String createProduct(Model model, Principal principal) {
		String username = principal.getName();	// get logged in username
		User user = userRepository.findByUsername(username);
		Long userId = user.getUserId();
		
		model.addAttribute("product", new Product());
		model.addAttribute("brands", brandRepository.findAll());
		model.addAttribute("categories", categoryRepositor.findAll());
		model.addAttribute("publicationDate", new Date());
		model.addAttribute("genders", Gender.values());
		model.addAttribute("seller", userId);
		return "addproduct";
	}
	
	@PostMapping("/saveproduct")
	public String saveProduct(@ModelAttribute Product product) {
		productRepository.save(product);
		return "redirect:/productlist";
	}

	@GetMapping("/delete/product/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable(value = "id") Long id) {
		productRepository.deleteById(id);
		return "redirect:/productlist";
	}
	
	@GetMapping("/edit/product/{id}/{user}")
	@PreAuthorize("#username == authentication.principal.username or hasAuthority('ADMIN')")
	public String editProduct(@PathVariable(value = "id") Long id, @PathVariable(value = "user") String username, Model model) {
		model.addAttribute("product", productRepository.findById(id));
		model.addAttribute("brands", brandRepository.findAll());
		model.addAttribute("categories", categoryRepositor.findAll());
		model.addAttribute("genders", Gender.values());
		return "editproduct";	
	}
	/*
	@GetMapping("/ownproductlist")
	public String findUserProducts(Model model, Principal principal) {
		String username = principal.getName();
		System.out.println("HOHOHOHOOO :" + username);
		//List<Product> products = new ArrayList<Product>();
		//products = productRepository.findBySeller(username);
		model.addAttribute("products", productRepository.findByOwner(username));
		// System.out.println(products);
		return "ownproductlist";
	}
	// TODO: käyttäjäkohtaiset tuotelistaukset
	*/

}
