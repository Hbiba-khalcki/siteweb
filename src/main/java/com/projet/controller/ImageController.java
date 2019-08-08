package com.projet.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.Entity.Image;
import com.projet.Entity.Publication;
import com.projet.service.ImageService;

@Controller
@RequestMapping(value = "/profile/image")
public class ImageController {
	 @Autowired
	 private ImageService imageService;
	 
	 @RequestMapping(value = "/get", method = RequestMethod.GET, params = { "id" })
		@ResponseBody
		public String getImage(long id) {
			
			Image image = imageService.getImageById(id);
			
			return "image";

		}

	 @RequestMapping(value = "/save", method = RequestMethod.POST)
		@ResponseBody
		public String save(@RequestBody Image image) {
		
			boolean saved = imageService.saveImage(image);
			
			return "image";
		}
	 
		@RequestMapping(value = "/delete", method = RequestMethod.DELETE, params = { "id" })
		@ResponseBody
		public String deleteImage(long id) {
		
			boolean deleted = imageService.deleteImage(id);
			
			return "image";
		}

		@RequestMapping(value = "/update", method = RequestMethod.PUT)
		@ResponseBody
		public String update(@RequestBody Image image) {
			
			boolean updated = imageService.updateImage(image);
			
			return "image";

		}
	}

	 
	 
	 
	 
	 


