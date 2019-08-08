package com.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.Entity.Image;
import com.projet.Entity.Publication;
import com.projet.dao.PublicationDao;

@Service
public class PublicationService {
 @Autowired
private PublicationDao publicationDao;
 public boolean savePublication(Publication publication) {
		try {
			publicationDao.savePublication(publication);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean updatePublication(Publication publication) {
		try {
			publicationDao.updatePublication(publication);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean deletePublication(long id) {
		try {
			Publication publication = publicationDao.getPublicationById(id);
			publicationDao.deletePublication(publication);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public Publication getPublicationById(long id) {
		try {
			return publicationDao.getPublicationById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
	public List<Publication> getallpublication(){
		try {
			return publicationDao.findAllPublication();
			
		} catch(Exception e) {
			return null;
		}
		
	}

	public Image getimagebypubid(Long pubid) {
		try {
			return publicationDao.findimage(pubid);
			
		} catch(Exception e) {
			return null;
		}
	}

	public List<Publication> getallUserPublication(Long userid) {
		try {
			return publicationDao.findAllUserPublication(userid);
			
		} catch(Exception e) {
			return null;
		}}
}

 
 
 

