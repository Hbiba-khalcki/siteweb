package com.projet.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.Entity.Image;
import com.projet.Entity.Publication;
import com.projet.Entity.User;
import com.projet.service.ImageService;
import com.projet.service.PublicationService;
import com.projet.service.UserService;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping(value = "/profile/publications")
public class PublicationController {
	@Autowired
	private PublicationService publicationService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private UserService userService;
	// win n7otou l fichier//
	public static String uploadDirectory = Paths.get("").toAbsolutePath().toString()
			+ "\\src\\main\\resources\\static\\upload\\";
	

	@GetMapping()
	public String publicationIndex(Model model) {
		Long userId =  userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId();

		List<Publication> pub = publicationService.getallUserPublication(userId);
		
		
		model.addAttribute("pub", pub);
		return "publications";
	}

	@PostMapping()
	public String save(@ModelAttribute("Publication") Publication publication,
			// upload file//
			@RequestParam("image") MultipartFile[] image) {
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
			User user =  userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
			publication.setIdUser(user);
			publicationService.savePublication(publication);
			String url = image[0].getOriginalFilename();
			Image i = new Image(nom, extension, url, publication);
			imageService.saveImage(i);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/profile/publications";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = { "id" })
	public String deletePublication(String id) {
		Long pid = Long.parseLong(id);
		boolean deleted = publicationService.deletePublication(pid);
		return "redirect:/profile/publications";
	}

	@PostMapping(value = "/put")
	public String update(@RequestBody Publication publication) {
		boolean updated = publicationService.updatePublication(publication);
		return "redirect:/profile/publications";

	}
}
