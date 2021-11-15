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

import hh.swd20.golfshop.domain.Brand;
import hh.swd20.golfshop.domain.BrandRepository;

/**
 * Controller-class to Brand, only for "user" who has authority 'ADMIN'
 **/

@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class BrandController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	// gets all brands from the database
	@GetMapping("/brandlist")
	public String getAllBrands(Model model) {
		model.addAttribute("brands", brandRepository.findAll());
		return "brandlist";	// TODO: html?
	}
	
	// empty brand form
	@GetMapping("/newbrand")
	public String createNewBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "addbrand";
	}
		
	// input from brand form, returns back if errors, otherwise saves brand
	@PostMapping("/savebrand")
	public String saveBrand(@Valid @ModelAttribute Brand brand, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "addbrand";
		} else {
			brandRepository.save(brand);
			return "redirect:brandlist";
		}
	}
	
	// edit form, pre-filled with information saved in the database
	@GetMapping("/edit/brand/{id}")
	public String editBrand(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("brand", brandRepository.findById(id));
		return "editbrand";
	}
	
	// input from edited brand form, returns back if errors, otherwise updates brand
	@PostMapping("/updatebrand")
	public String updateBrand(@Valid @ModelAttribute Brand brand, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "editbrand";
		} else {
			brandRepository.save(brand);
			return "redirect:brandlist";
		}
	}
	
	// delete brand from the database
	@GetMapping("/delete/brand/{id}")
	public String deleteBrand(@PathVariable(value = "id") Long id) {
		brandRepository.deleteById(id);
		return "redirect:/brandlist";
	}

}
