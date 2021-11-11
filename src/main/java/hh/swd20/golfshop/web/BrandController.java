package hh.swd20.golfshop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.golfshop.domain.Brand;
import hh.swd20.golfshop.domain.BrandRepository;

@PreAuthorize("hasAuthority('ADMIN')")
@Controller
public class BrandController {
	
	@Autowired
	private BrandRepository brandRepository;
	
	@GetMapping("/brandlist")
	public String getAllBrands(Model model) {
		model.addAttribute("brands", brandRepository.findAll());
		return "brandlist";	// TODO: html?
	}
	
	@GetMapping("/newbrand")
	public String createNewBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "addbrand";
	}
	
	@PostMapping("/savebrand")
	public String saveBrand(@ModelAttribute Brand brand) {
		brandRepository.save(brand);
		return "redirect:/brandlist";
	}
	
	@GetMapping("/edit/brand/{id}")
	public String editBrand(@PathVariable(value = "id") Long id, Model model) {
		model.addAttribute("brand", brandRepository.findById(id));
		return "editbrand";
	}
	
	@GetMapping("/delete/brand/{id}")
	public String deleteBrand(@PathVariable(value = "id") Long id) {
		brandRepository.deleteById(id);
		return "redirect:/brandlist";
	}
		
		// TODO: REST

}
