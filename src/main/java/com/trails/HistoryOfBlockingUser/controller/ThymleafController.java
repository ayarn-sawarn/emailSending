package com.trails.HistoryOfBlockingUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trails.HistoryOfBlockingUser.model.Users;
import com.trails.HistoryOfBlockingUser.service.UserServiceImpl;

@RestController
public class ThymleafController {
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/thusers/getall")
	public String viewHomePage(Model model) {
        model.addAttribute("listUsers",userService.getAllUsers());
        return "Users";
    }
	
	@PostMapping("/thusers/create")
    public String saveEmployee(@ModelAttribute("employee") Users users) {
        // save employee to database
        userService.createUser(users);
        return "redirect:/Users";
    }
	
	 @GetMapping("/showFormForUpdate/{id}")
	   public String updateUsers(@PathVariable(value = "id") String id, Model model) {
	       // set employee as a model attribute to pre-populate the form
	        //model.addAttribute("users",users);
	        return "update_employee";
	    }
	

}
