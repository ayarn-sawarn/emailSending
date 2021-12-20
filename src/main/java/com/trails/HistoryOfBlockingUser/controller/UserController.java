package com.trails.HistoryOfBlockingUser.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trails.HistoryOfBlockingUser.model.Users;
import com.trails.HistoryOfBlockingUser.service.UserServiceImpl;
@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	@PostMapping("/users")
	public Users createUser(@RequestBody Users user) {
		return userService.createUser(user);
	}
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUser(){
		return ResponseEntity.ok().body(userService.getAllUsers());
	}

	
	@GetMapping("/users/{id}")
	public Optional<Users> getUserById(@PathVariable("id") String id) {
		return Optional.of(userService.getUserById(id));
	}
	
	/*@PutMapping("/users/block")
	public Users blockUser(@RequestParam String uId ,@RequestParam String id) {
		return this.userService.blockUser(uId, id);
	}
	@PutMapping("/users/unblock")
	public Users unblock(@RequestParam String uId, @RequestParam String id) {
		return this.userService.unblockUser(uId, id);
	}
	*/
	
	@GetMapping("/users/forget/{id}")
	public Users forgetPassowrd(@PathVariable("id") String id) {
		Users user;
		user = userService.forgetPassword(id);
		return user;
	}
	@GetMapping("/users/login")
	public Users login(@RequestParam String id, @RequestParam String password) {
		 Users u = userService.loginUser(id, password);
		if(u!=null) {
			return u;
		}
		else {
			return null;
		}
	}
	@PutMapping("/users/reset")
	public String resetPassword(@RequestParam String id, @RequestParam Integer otp, @RequestParam String newpass) {
		boolean sucess = userService.resetPassword(id, otp, newpass);
		if(sucess) {
			return "Password Changed";		
		}
		else {
			return "not changed";
		}
	}



}
