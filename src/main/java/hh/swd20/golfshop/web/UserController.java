package hh.swd20.golfshop.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.swd20.golfshop.domain.SignUpForm;
import hh.swd20.golfshop.domain.User;
import hh.swd20.golfshop.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/signup")
	public String createUser(Model model) {
		model.addAttribute("signupform", new SignUpForm());
		return "signup";
	}
	
	@PostMapping("/saveuser")
	public String saveUser(@Valid @ModelAttribute("signupform") SignUpForm signUpForm, BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {	// validation errors
			if (signUpForm.getPassword().equals(signUpForm.getPasswordCheck())) {	// checking password match
				String pwd = signUpForm.getPassword();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String hashPwd = bc.encode(pwd);
				
				User newUser = new User();
				newUser.setPasswordHash(hashPwd);
				newUser.setUsername(signUpForm.getUsername());
				newUser.setRole("USER");
				newUser.setFirstName(signUpForm.getFirstName());
				newUser.setLastName(signUpForm.getLastName());
				newUser.setPhoneNumber(signUpForm.getPhoneNumber());
				newUser.setEmail(signUpForm.getEmail());
				
				if (userRepository.findByUsername(signUpForm.getUsername()) == null) {	// checking if  user exists
					userRepository.save(newUser);
				} else {
					bindingResult.rejectValue("username", "err.username", "Username already exists");
					return "signup";
				}
			} else {
				bindingResult.rejectValue("passwordCheck", "err.passwordCheck", "Passwords do not match");
				return "signup";
			}
		} else {
			return "signup";
		}
		return "redirect:/login";
	}
	
	@GetMapping("/myaccount/{user}")
	@PreAuthorize("#username == authentication.principal.username or hasAuthority('ADMIN')")
	public String findUserById(@PathVariable(value = "user") String username, Model model, Principal principal) {
		User user = userRepository.findByUsername(username);
		model.addAttribute("user", user);
		return "useraccount";
		
	}

}
