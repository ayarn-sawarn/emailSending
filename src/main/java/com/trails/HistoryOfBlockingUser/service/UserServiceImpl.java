package com.trails.HistoryOfBlockingUser.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trails.HistoryOfBlockingUser.model.BlockedUser;
import com.trails.HistoryOfBlockingUser.model.UnblockedUser;
import com.trails.HistoryOfBlockingUser.model.Users;
import com.trails.HistoryOfBlockingUser.repository.UserRepository;

@Service
public class UserServiceImpl {
	@Autowired
	UserRepository userRepository;
	@Autowired
	EmailService emailService;
		
	//createing user
	public Users createUser(Users u) {
		
		//List<UnblockedUser> ub = new ArrayList<>();
		//ub.add(new UnblockedUser("0","unblocked",new Date()));
		//List<BlockedUser> bus = new ArrayList<>();
		//bus.add(new BlockedUser("0","blocked",new Date()));
	    
		
		//u.setUserCreateddate(new Date());
		//u.setBuser(bus);
		//u.setUnuser(ub);
		return  this.userRepository.save(u);
	}
	//getting all users
	public List<Users> getAllUsers(){
		return this.userRepository.findAll();
	}
	//getiing particular
	public Users getUserById(String id) {
		Users us=null;
		Optional<Users> ops = this.userRepository.findById(id);
		if(ops.isPresent()) {
			us=ops.get();
		}
		return us;
	}
	//blocking of users
	/*
	public Users blockUser(String uid, String id) {
		BlockedUser buser= new BlockedUser();
		Users oldusr = null;
		buser.setBid(id);
		buser.setStatus("Blocked User");
		buser.setBlockedDate(new Date());
		System.out.println(buser.toString());
		List<BlockedUser> b = new ArrayList<>();
		//b.add(buser);
		Optional<Users> user = this.userRepository.findById(uid);
		if(user.isPresent()) {
			Users ui = user.get();
			//b = ui.getBuser();
			System.out.println(b);
			///b.add(buser);
			//ui.setBuser(b);
			oldusr = this.userRepository.save(ui);
			
		}
		return oldusr;
	}
	public Users unblockUser(String uid, String id) {
		Users updated = null;
		UnblockedUser unblocked = new UnblockedUser();
		unblocked.setUnbid(id);
		unblocked.setStatus("unblocked");
		unblocked.setDate(new Date());
		System.out.println(unblocked);
		List<UnblockedUser> ub = new ArrayList<>();
		//ub.add(unblocked);
		Optional<Users> ui= this.userRepository.findById(uid);
		if(ui.isPresent()) {
			Users olduser= ui.get();
			ub = olduser.getUnuser();
			if(ub.isEmpty()) {
				ub.add(unblocked);
				System.out.println(ub);
			}
			else {
				System.out.println(ub);
				ub.add(unblocked);
				System.out.println(ub);
			}
			System.out.println(ub);
			ub.add(unblocked);
			System.out.println(ub);
			 olduser.setUnuser(ub);
			 System.out.println(olduser.toString());
			 updated = this.userRepository.save(olduser);
		}
			
		
		return updated;
	}
	
	*/
	
	
	//forget user
	public Users forgetPassword(String id) {
		Optional<Users> ui = this.userRepository.findById(id);
		Users u =null;
		if(ui.isPresent()) {
			Users user = ui.get();
			u=user;
			String email = user.getEmail();
			Random random = new Random();
			int otp = random.nextInt(9999);
			System.out.println("our otp is " +otp);
			String str = "Your otp is"+otp;
			
			//sending mail
			emailService.sendEmail(email, str);
			System.out.println("mail service is triggered");
			
			//saving in db
			user.setOtp(otp);
			this.userRepository.save(user);
	
			
		}
		return u;
	}
	//login api
	public Users loginUser(String id,String password) {
		Users avilable = null;
		Optional<Users> ui = this.userRepository.findById(id);
		if(ui.isPresent()) {
			Users user = ui.get();
			String pass = user.getPassword();
			if(pass.equalsIgnoreCase(password)) {
				avilable = user;
			}else {
				avilable=null;
			}
			
		}
		return avilable;
	}
	//password setting
	public boolean resetPassword(String id, Integer otp, String newPass) {
		boolean status=false;
		Optional<Users> u = this.userRepository.findById(id);
		if(u.isPresent()) {
			Users user = u.get();
			int dbotp = user.getOtp();
			if(dbotp==otp) {
				user.setPassword(newPass);
				this.userRepository.save(user);
				status=true;
			}
		}
		else {
			status=false;
		}
		return status;
	}
	
}
