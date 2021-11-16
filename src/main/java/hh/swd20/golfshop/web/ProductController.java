package hh.swd20.golfshop.web;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

/**
 * Controller-class to Product
 **/

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

	// REST : all products
	@CrossOrigin
	@GetMapping("/products")
	public @ResponseBody List<Product> productListRest() {
		return (List<Product>) productRepository.findAll(); 
	}
	
	// all products from the database, home page
	@GetMapping("/")
	public String getAllProducts(Model model) {
		model.addAttribute("products", productRepository.findAll());
		return "productlist";
	}
	
	// find by id, shows more detailed product-info to users
	@GetMapping("/product/{id}")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public String findProductById(@PathVariable(value = "id") Long id, Model model) {
		if (productRepository.findById(id).isEmpty()) {
			return "errormsg";
		}
		Optional<Product> product = productRepository.findById(id);
		model.addAttribute("product", product.get());
		return "productdetails";
	}
	
	// empty form for a new product
	// only logged in user or admin can add product to database
	@GetMapping("/newproduct")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
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
	
	// input from product form, returns back if errors, otherwise saves product
	@PostMapping("/saveproduct")
	public String saveProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model, Principal principal) {
		if (bindingResult.hasErrors()) {
			String username = principal.getName();
			User user = userRepository.findByUsername(username);
			Long userId = user.getUserId();
			
			model.addAttribute("brands", brandRepository.findAll());
			model.addAttribute("categories", categoryRepositor.findAll());
			model.addAttribute("publicationDate", new Date());
			model.addAttribute("genders", Gender.values());
			model.addAttribute("seller", userId);
			return "addproduct";
		} else {
			productRepository.save(product);
			return "redirect:/";
		}
	}
	
	// edit form, pre-filled with information saved in the database
	// users can edit only their own products, admin can edit all products from the database
	@GetMapping("/edit/product/{id}/{user}")
	@PreAuthorize("#username == authentication.principal.username or hasAuthority('ADMIN')")
	public String editProduct(@PathVariable(value = "id") Long id, @PathVariable(value = "user") String username, Model model) {
		if (productRepository.findById(id).isEmpty()) {
			return "errormsg";
		}
		model.addAttribute("product", productRepository.findById(id));
		model.addAttribute("brands", brandRepository.findAll());
		model.addAttribute("categories", categoryRepositor.findAll());
		model.addAttribute("genders", Gender.values());
		return "editproduct";	
	}
	
	// input from edited product form, returns back if errors, otherwise updates product
	@PostMapping("/updateproduct")
	public String updateProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("brands", brandRepository.findAll());
			model.addAttribute("categories", categoryRepositor.findAll());
			model.addAttribute("genders", Gender.values());
			model.addAttribute("product", product);
			return "editproduct";
		} else {
			productRepository.save(product);
			return "redirect:/";
		}
	}
	
	// delete product
	@GetMapping("/delete/product/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable(value = "id") Long id) {
		try {
			productRepository.deleteById(id);
			return "redirect:/";
		} catch (Exception e) {
			return "errormsg";
		}
	}
	
}
