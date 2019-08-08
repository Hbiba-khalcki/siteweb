package com.projet.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projet.Entity.Publication;
import com.projet.Entity.SliderImage;
import com.projet.Entity.User;
import com.projet.service.ImageService;
import com.projet.service.PublicationService;
import com.projet.service.SliderImageService;
import com.projet.service.UserService;

import org.springframework.ui.Model;
@Controller
public class MainController {
	@Autowired
	private PublicationService publicationService;
	@Autowired
	public JavaMailSender emailSender;
	@Autowired
	private SliderImageService sliderImageService;
	@Autowired
	private UserService userService;

    @GetMapping("/")
    public String root(Model model) {
    	List<Publication> pub = publicationService.getallpublication();
    	List<SliderImage> sliderimg = sliderImageService.getallsliderimg();
		Long firstimgid=sliderimg.get(0).getId();
		 model.addAttribute("sliderimg", sliderimg);
		 model.addAttribute("firstimgid", firstimgid);
	    model.addAttribute("pub", pub);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    
    // get current user //

    @GetMapping("/profile")
    public String userIndex(Principal principal , Model model) {
    	String userName = principal.getName();
    	User user = userService.findByEmail(userName);
    	 model.addAttribute("user", user);
        return "profile";
    }

 
    @GetMapping("/contact")
    public String contactIndex(Model model) {
    	
        return "contact";
    }
    @PostMapping("/contact")
    public String sendMail(String name, String email , String msg) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(email);
        message.setSubject(name);
        message.setText(msg);
        try {
            this.emailSender.send(message);
        	return  ("redirect:/contact?success");
        }catch(Exception e) {
        	return  ("redirect:/contact?error");
        }
        	
        
    }
    
    @GetMapping("/about")
    public String aboutIndex() {
        return "about";
    }
    @GetMapping("/awards")
    public String awardsIndex() {
        return "awards";
    }

    @GetMapping("/loginsuccessredirect")
    public String redirectUserAdmin(Authentication authentication){
        if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            return ("redirect:/admin");
        }
        else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
            return ("redirect:/profile");
        }
        else{
            return ("redirect:/login");
        }
    }
    
    
    @GetMapping("/publication/{id}")
    public String publicationinfo(Model model ,@PathVariable Long id) {
    	
    	Publication pub= publicationService.getPublicationById(id);
    	User user = (userService.findById(pub.getIdUser().getId())).get();
    	model.addAttribute("pub", pub);
    	model.addAttribute("user",user);
    	
    	
    	return "publicationinfo";
    }
}
