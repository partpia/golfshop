package hh.swd20.golfshop.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.golfshop.domain.Category;
import hh.swd20.golfshop.domain.CategoryRepository;

/**
 * Controller-class to Category, only for "user" who has authority 'ADMIN'
 **/

@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// gets all categoriess from the database
	@GetMapping("categorylist")
	public String getAllCategories(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categorylist";
	}
	
	// empty category form
	@GetMapping("/newcategory")
	public String createNewCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	
	// input from category form, returns back if errors, otherwise saves category
	@PostMapping("/savecategory")
	public String saveCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addcategory";
		} else {
			categoryRepository.save(category);
			return "redirect:/categorylist";
		}
	}
	
	// edit form, pre-filled with information saved in the database
	@GetMapping("/edit/category/{id}")
	public String editCategory(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("category", categoryRepository.findById(id));
		return "editcategory";
	}
	
	// input from category form, returns back if errors, otherwise updates category
	@PostMapping("/updatecategory")
	public String updateCategory(@Valid @ModelAttribute Category category, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editcategory";
		} else {
			categoryRepository.save(category);
			return "redirect:/categorylist";
		}
	}
	
	// delete category from the database
	@GetMapping("/delete/category/{id}")
	public String deleteCategory(@PathVariable(value = "id") Long id) {
		categoryRepository.deleteById(id);
		return "redirect:/categorylist";
	}

}
