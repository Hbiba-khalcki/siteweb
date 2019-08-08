package com.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Entity.SliderImage;
import com.projet.dao.ImageDao;
import com.projet.dao.SliderImageDao;

@Service
public class SliderImageService {
	 @Autowired
	 private SliderImageDao sliderImageDao;
	 
	 public boolean saveSliderImage(SliderImage sliderImage) {
			try {
				sliderImageDao.saveSliderImage(sliderImage);
				return true;

			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}

		public boolean updateSliderImage(SliderImage sliderImage) {
			try {
				sliderImageDao.updateSliderImage(sliderImage);
				return true;

			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}

		}

		public boolean deleteSliderImage(long id) {
			try {
				SliderImage sliderImage = sliderImageDao.getSliderImageById(id);
				sliderImageDao.deleteSliderImage(sliderImage);
				return true;

			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}

		}

		public SliderImage getSliderImageById(long id) {
			try {
				return sliderImageDao.getSliderImageById(id);
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}

		}

		public List<SliderImage> getallsliderimg() {
			try {
				return sliderImageDao.getallSliderImage();
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}

		}


	}



