package com.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.projet.Entity.Publication;
import com.projet.Entity.SliderImage;





@Repository
@Transactional
public class SliderImageDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void saveSliderImage(SliderImage sliderImage) {
		entityManager.persist(sliderImage);
	}

	public void updateSliderImage(SliderImage sliderImage) {
		entityManager.merge(sliderImage);
	}

	public void deleteSliderImage(SliderImage sliderImage) {
		entityManager.remove(sliderImage);
	}

	public SliderImage getSliderImageById(long id) {
		return entityManager.find(SliderImage.class, id);
	}

	public List<SliderImage> getallSliderImage() {
		  List<SliderImage> sliderimg = entityManager.createQuery("SELECT sliderImage FROM SliderImage sliderImage").getResultList();
		    
		    return sliderimg; 
	}

}