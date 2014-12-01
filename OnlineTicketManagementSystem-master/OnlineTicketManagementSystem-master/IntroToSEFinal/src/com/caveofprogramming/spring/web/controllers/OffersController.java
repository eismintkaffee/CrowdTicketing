package com.caveofprogramming.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import com.caveofprogramming.spring.web.dao.Buytkts;
import com.caveofprogramming.spring.web.dao.FormValidationGroup;
import com.caveofprogramming.spring.web.dao.Offer;
import com.caveofprogramming.spring.web.dao.User;
import com.caveofprogramming.spring.web.service.OffersService;
import com.caveofprogramming.spring.web.service.UsersService;

@Controller
public class OffersController {

	private OffersService offersService;

	@Autowired
	public void setOffersService(OffersService offersService) {
		this.offersService = offersService;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String showTest(Model model, @RequestParam("id") String id) {
		System.out.println("Id is: " + id);
		return "home";
	}

	/*
	 * @ExceptionHandler(DataAccessException.class) public String
	 * handleDatabaseException(DataAccessException ex) { return "error";
	 */


	@RequestMapping("/createoffer")
	public String createOffer(Model model, Principal principal) {

		Offer offer = null;

		if (principal != null) {
			String username = principal.getName();

			offer = offersService.getOffer(username);
		}

		if (offer == null) {
			offer = new Offer();
		}

		model.addAttribute("offer", offer);

		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Validated(value=FormValidationGroup.class) Offer offer,
			BindingResult result, Principal principal,
			@RequestParam(value = "delete", required = false) String delete) {

		if (result.hasErrors()) {
			return "createoffer";
		}
		
		if(delete == null) {
			String username = principal.getName();
			offer.getUser().setUsername(username);
			offersService.saveOrUpdate(offer);
			return "offercreated";
		}
		else {
			offersService.delete(offer.getId());
			return "offerdeleted";
		}
		
	}
	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String buypost(Model model,Offer offer,Principal principals) {
		Buytkts buytkt = new Buytkts();
		String username= principals.getName();
		Offer offer1 =  (Offer) offersService.getOffer(offer.getId()).get(0);
		System.out.println(offer1);
		int avl = offer1.getAvailable() - offer.getTotal();
		offer1.setAvailable(avl);
		buytkt.setId(offer.getId());
		buytkt.setUsername(username);
		buytkt.setTnum(offer.getTotal());
		buytkt.setDate(offer1.getDate());
		buytkt.setTitle(offer1.getTitle());
		buytkt.setType(offer1.getType());
		buytkt.setVenue(offer1.getVanue());
		offersService.save(buytkt);
		offersService.saveOrUpdate(offer1);
		return "test";
	}
	
	@RequestMapping(value = "/mytkts", method = RequestMethod.POST)
	public String buytkt(Model model) {
		
	//	model.addAttribute("",new buy)
		return "usertkts";
		
	}
	
	
	@RequestMapping("/events")
	public String showEvents(Model model, Principal principal) {
		String username = principal.getName();
		List<Buytkts> eventlist = offersService.getCurrentevent(username);

		model.addAttribute("offers", eventlist);
		
		return "myevents";
	}
	
	
}
