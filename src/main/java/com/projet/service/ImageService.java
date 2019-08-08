package com.projet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Entity.Image;
import com.projet.Entity.Publication;
import com.projet.dao.ImageDao;

@Service
public class ImageService {
	 @Autowired
	 private ImageDao imageDao;
	 public boolean saveImage(Image image) {
			try {
				imageDao.saveImage(image);
				return true;

			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}

		public boolean updateImage(Image image) {
			try {
				imageDao.updateImage(image);
				return true;

			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}

		}

		public boolean deleteImage(long id) {
			try {
				Image image = imageDao.getImageById(id);
				imageDao.deleteImage(image);
				return true;

			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}

		}

		public Image getImageById(long id) {
			try {
				return imageDao.getImageById(id);
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}

		}


	}

	 
	 
	 

