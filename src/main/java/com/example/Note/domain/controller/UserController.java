package com.example.Note.domain.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.Note.domain.User;
import com.example.Note.domain.UserRepository;
import com.example.Note.domain.registerform;

@Controller
public class UserController {

	@Autowired
	private UserRepository URepository;
	
	/**
	 * list All Username 
	 * authority: ADMIN
	 */
	@RequestMapping(value = "/userlist")
	public String userList(Model model) {
		model.addAttribute("users", URepository.findAll());
		return "User/userlist";
	}
	/**
	 * Delete User 
	 * authority: ADMIN
	 */
	@RequestMapping(value = "/deleteuser/{userid}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteUser(@PathVariable("userid") Long userid, Model model) {
		URepository.deleteById(userid);
		return "redirect:/userlist";
	}
	/**
	 * Register form
	 *
	 */
	@RequestMapping(value = "/register")
	public String addUser(Model model) {
		model.addAttribute("registerform", new registerform());
		return "/register";
	}
	/**
	 * Add new User to database
	 * validating data
	 * Default USER only
	 *
	 */
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveNewUser(@Valid @ModelAttribute("registerform") registerform register, BindingResult binding) {

		if (binding.hasErrors() == false) {
			if (register.getPassword().equals(register.getPasswordCheck())) {
				User nUser = new User();
				BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
				String passhash = bc.encode(register.getPassword());

				nUser.setPassword(passhash);
				nUser.setUsername(register.getUsername());
				nUser.setRole("USER");
				if (URepository.findByUsername(register.getUsername()) == null) {
					URepository.save(nUser);
					System.out.print(nUser);
				} else {
					binding.rejectValue("username", "err.username", "Username already exists");
					return "register";
				}
			} else {
				binding.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
				return "register";
			}
		} else {
			return "register";
		}

		return "redirect:/login";
	}
}
