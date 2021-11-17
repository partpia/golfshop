package hh.swd20.golfshop.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController implements ErrorController {
	
	private static final String PATH = "/error";
	
	@GetMapping(value = PATH)
	public String errorMessage() {
		return "errormsg";
	}
	
}
