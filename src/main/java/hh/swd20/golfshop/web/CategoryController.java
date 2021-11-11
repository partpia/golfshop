package hh.swd20.golfshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.golfshop.domain.Category;
import hh.swd20.golfshop.domain.CategoryRepository;

@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping("categorylist")
	public String getAllCategories(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categorylist";
	}
	
	@GetMapping("/newcategory")
	public String createNewCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	@PostMapping("/savecategory")
	public String saveCategory(@ModelAttribute Category category) {
		categoryRepository.save(category);
		return "redirect:/categorylist";
	}
	
	@GetMapping("/edit/category/{id}")
	public String editCategory(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("category", categoryRepository.findById(id));
		return "editcategory";
	}

	@GetMapping("/delete/category/{id}")
	public String deleteCategory(@PathVariable(value = "id") Long id) {
		categoryRepository.deleteById(id);
		return "redirect:/categorylist";
	}
	// TODO: REST

}
