package com.projet.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Entity.Image;
import com.projet.Entity.Publication;
import com.projet.Entity.SliderImage;
import com.projet.Entity.User;
import com.projet.service.ImageService;
import com.projet.service.PublicationService;
import com.projet.service.SliderImageService;
import com.projet.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private UserService userService;
	@Autowired
	private SliderImageService sliderImageService;
	public static String uploadDirectory = Paths.get("").toAbsolutePath().toString()
			+ "\\src\\main\\resources\\static\\upload\\";
	
	

    @GetMapping()
    public String adminIndex(Model model) {
    	
    		List<User> users = userService.findAll();
    		
    		model.addAttribute("users", users);
    		
        return "admin";
    }
	
	@GetMapping("/publications")
	public String publicationIndex(Model model) {
		List<Publication> pub = publicationService.getallpublication();
	    model.addAttribute("pub", pub);
		return "adminpublication";
	}
	@PostMapping("/publications/confirm")
	public String confirmpublication(long id) {
		
		Publication pub = publicationService.getPublicationById(id);
		pub.setConfirm(true);
		pub.setCancel(false);
		publicationService.updatePublication(pub);
		return"redirect:/admin/publications";
		
	}
	@PostMapping("/publications/cancel")
	public String cancelpublication(long id) {
		
		Publication pub = publicationService.getPublicationById(id);
		pub.setCancel(true);
		pub.setConfirm(false);
		publicationService.updatePublication(pub);
		return"redirect:/admin/publications";
		
	}


	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)

	public String infopubuser(Model model ,@PathVariable Long id) {
		User user = (userService.findById(id)).get();
		System.out.println(user.toString());
		List<Publication> pub = publicationService.getallUserPublication(id);
		 model.addAttribute("pub", pub);
		 model.addAttribute("user", user);
		return "adminuserinfo";
		
	}
	@GetMapping("/slider")
	public String slider(Model model) {
		List<SliderImage> sliderimg = sliderImageService.getallsliderimg();
		 model.addAttribute("sliderimg", sliderimg);
		
		return "sliderimage";
	}
	@PostMapping("/slider")
	public String addSliderimage(@RequestParam("image") MultipartFile[] image) {
		String nom = image[0].getOriginalFilename();
		String extension = image[0].getContentType();
		// bufferedoutputstream liyekteb l fichier//
		try {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(uploadDirectory + image[0].getOriginalFilename()));
			bos.write(image[0].getBytes());
			bos.flush();
			bos.close();
			// fin upload file//
			
			
			String url = image[0].getOriginalFilename();
			SliderImage i = new SliderImage(nom, extension, url);
			sliderImageService.saveSliderImage(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/slider";
	}
	
	
	@PostMapping("/slider/delete")
	public String deleteslider(Long id) {
		sliderImageService.deleteSliderImage(id);
		return "redirect:/admin/slider";
	}
		
	



	

}
