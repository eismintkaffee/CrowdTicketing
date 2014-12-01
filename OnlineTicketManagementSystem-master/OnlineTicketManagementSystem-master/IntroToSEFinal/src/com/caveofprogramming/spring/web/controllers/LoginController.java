package com.caveofprogramming.spring.web.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.caveofprogramming.spring.web.dao.FormValidationGroup;
import com.caveofprogramming.spring.web.dao.Message;
import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.UsersService;

@Controller
public class LoginController {
	
	
	
	@Autowired
	private MailSender mailSender;
	
	private UsersService usersService;
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/login")
	public String showLogin() {
		return "login";
	}
	
	@RequestMapping("/denied")
	public String showDenied() {
		return "denied";
	}
	
	@RequestMapping("/messages")
	public String showMessages() {
		return "messages";
	}
	
	@RequestMapping("/admin")
	public String showAdmin(Model model) {
		
		List<User> users = usersService.getAllUsers();
		
		model.addAttribute("users", users);
		
		return "admin";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut() {
		return "loggedout";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "newaccount";
	}
	

	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(@Validated(FormValidationGroup.class) User user,@RequestParam("file") File file, BindingResult result) throws IOException {
		
		if(result.hasErrors()) {
			return "newaccount";
		}
		System.out.println(file.getAbsoluteFile());
		System.out.println(file.exists());
		user.setAuthority("ROLE_USER");
		user.setEnabled(true);
		
		if(usersService.exists(user.getUsername())) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		try {
			 byte[] bFile = new byte[(int) file.length()];
			 try {
			     FileInputStream fileInputStream = new FileInputStream(file);
			     //convert file into array of bytes
			     fileInputStream.read(bFile);
			    // fileInputStream.
			     fileInputStream.close();
			     FileOutputStream fos = new FileOutputStream("C:\\Users\\Ramya\\workspace\\spring-tutorial-169\\WebContent\\resources\\images\\xxxx.jpeg"); 
		            fos.write(bFile);
		            fos.close();
		        } catch (Exception e) {
			     e.printStackTrace();
		        }
			 System.out.println(bFile);
			 user.setImage(bFile);
			//Blob blob = Hibernate.createBlob(file.getInputStream());
			//user.setContent(blob);
			usersService.create(user);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		
		
		return "accountcreated";
	}
	
	@RequestMapping(value="/getmessages", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Map<String, Object> getMessages(Principal principal) {
		
		List<Message> messages = null;
		
		if(principal == null) {
			messages = new ArrayList<Message>();
		}
		else {
			String username = principal.getName();
			messages = usersService.getMessages(username);
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("messages", messages);
		data.put("number", messages.size());
		
		return data;
	}
	
	@RequestMapping(value="/sendmessage", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data) {
		
		
		String text = (String)data.get("text");
		String name = (String)data.get("name");
		String email = (String)data.get("email");
		Integer target = (Integer)data.get("target");
		

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("learnhungarianfast@gmail.com");
		mail.setTo(email);
		mail.setSubject("Re: " + name + ", your message");
		mail.setText(text);
		
		try {
			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Can't send message");
		}

		Map<String, Object> rval = new HashMap<String, Object>();
		rval.put("success", true);
		rval.put("target", target);
		
		return rval;
	}
	
	@RequestMapping(value="/settings", method = RequestMethod.GET)
	public String createOrUpdateUserDetails(Model model,Principal principal){
		String username= principal.getName();
		User user =  (User) usersService.getUser(username);
		model.addAttribute("myuser",user);
		byte[] bAvatar = user.getImage();
		System.out.print(user);
		try{
            FileOutputStream fos = new FileOutputStream("C:\\Users\\Ramya\\workspace\\spring-tutorial-169\\WebContent\\resources\\images\\"+username+".jpeg"); 
            fos.write(bAvatar);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
		String path= "C:\\Users\\Ramya\\workspace\\spring-tutorial-169\\WebContent\\resources\\images\\"+username+".jpeg";
		model.addAttribute("path",path );
		
		model.addAttribute("user",new User());
		
		return "settings";
	}
	
	@RequestMapping(value="/settings", method = RequestMethod.POST)
	public String createOrUpdateDetails(Model model,User user,Principal principal,@RequestParam("file") File file){
		System.out.println(user);
		String username= principal.getName();
		User user2 = (User) usersService.getUser(username);
		System.out.println(user2);
		user2.setUsername(user.getUsername());
		user2.setEmail(user.getEmail());
		user2.setName(user.getName());
		user2.setPassword(user.getPassword());
		byte[] bFile = new byte[(int) file.length()];
		 try {
		     FileInputStream fileInputStream = new FileInputStream(file);
		     //convert file into array of bytes
		     fileInputStream.read(bFile);
		    // fileInputStream.
		     fileInputStream.close();
		   
	        } catch (Exception e) {
		     e.printStackTrace();
	        }
		 System.out.println(bFile);
		 
		 user2.setImage(bFile);
		System.out.println(user2);
		usersService.update(user2);
			
		return "test2";
	}
}
