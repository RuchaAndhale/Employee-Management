package com.rachana.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rachana.task2.model.User;
import com.rachana.task2.repository.UserRepository;
import com.rachana.task2.service.UserLogin;

@Controller
public class AppController {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserLogin userLogin;
	
	@GetMapping(value = "/")
	public ModelAndView loginPage() {
	    return new ModelAndView("index.html");
	}
	
	@GetMapping(value = "/register")
	public ModelAndView showSignUpForm(Model model) {
		model.addAttribute("users", new User());
		return new ModelAndView("signup_form.html");
	}
	
	@PostMapping(value="/process_register")
	public ModelAndView processRegistration(User user) {
		repo.save(user);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping(value = "/home")
	public ModelAndView showHomePage()
	{
		return new ModelAndView("home.html");
	}
	
	
	@GetMapping(value="/errorPage")
	public ModelAndView showErrorPage()
	{
		return new ModelAndView("alert.html");
	}
	
	@PostMapping(value="/process_login")
	public ModelAndView processLogin(User user) {
		boolean isPresent = userLogin.checkUser(user);
		if(isPresent)
		{
			return new ModelAndView("redirect:/home");
		}
		return new ModelAndView("redirect:/errorPage");		
	}
	
	@GetMapping(path="/getUsers") 
	public String getUserPage(Model model) {
		return "get_users.html";
	} 
	
	 
}
