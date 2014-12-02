package com.caveofprogramming.spring.web.controllers;

import java.io.FileOutputStream;
import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.OffersService;
import com.caveofprogramming.spring.web.service.UsersService;

@Controller
public class HomeController {

	private static Logger logger = Logger.getLogger(HomeController.class);
	
	
	private UsersService usersService;
	@Autowired
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	@Autowired
	private OffersService offersService;

	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {

		List<Offer> offers = offersService.getCurrent();

		model.addAttribute("offers", offers);
		model.addAttribute("offer",new Offer());
		
		
		boolean hasOffer = false;
		
		if(principal != null) {
			hasOffer = offersService.hasOffer(principal.getName());
			String username= principal.getName();
			User user =  (User) usersService.getUser(username);
			model.addAttribute("myuser",user);
			byte[] bAvatar = user.getImage();
			System.out.print(user);
			try{
	            FileOutputStream fos = new FileOutputStream("C:\\Users\\Tiffu_000\\Desktop\\project360\\CrowdTicketing\\OnlineTicketManagementSystem-master\\OnlineTicketManagementSystem-master\\IntroToSEFinal\\WebContent\\resources\\images\\"+username+".jpg"); 
	            fos.write(bAvatar);
	            fos.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
			String path= "C:\\Users\\Tiffu_000\\Desktop\\project360\\CrowdTicketing\\OnlineTicketManagementSystem-master\\OnlineTicketManagementSystem-master\\IntroToSEFinal\\WebContent\\resources\\images\\"+username+".jpg";
			model.addAttribute("path",path );
			
			
		}
		
		
		return "home";
	}

}
