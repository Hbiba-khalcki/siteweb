package com.projet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.projet.Entity.Image;
import com.projet.Entity.Publication;

@Repository
@Transactional
public class ImageDao {
	@PersistenceContext
	private EntityManager entityManager;

	public void saveImage(Image image) {
		entityManager.persist(image);
	}

	public void updateImage(Image image) {
		entityManager.merge(image);
	}

	public void deleteImage(Image image) {
		entityManager.remove(image);
	}

	public Image getImageById(long id) {
		return entityManager.find(Image.class, id);
	}

}


