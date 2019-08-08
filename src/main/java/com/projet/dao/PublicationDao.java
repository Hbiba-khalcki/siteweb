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
public class PublicationDao {
	@PersistenceContext
	private EntityManager entityManager;
 

	public void savePublication(Publication publication) {
		entityManager.persist(publication);
	}

	public void updatePublication(Publication publication) {
		entityManager.merge(publication);
	}

	public void deletePublication(Publication publication) {
		entityManager.remove(publication);
	}

	public Publication getPublicationById(long id) {
		return entityManager.find(Publication.class, id);
	}
	public List<Publication> findAllPublication() {
	    List<Publication> publications = entityManager.createQuery("SELECT publication FROM Publication publication").getResultList();
	    System.out.print(publications.toString());
	    return publications;    
	}
// limage publication //
	public Image findimage(Long pubid) {
		Query query  = entityManager.createQuery("SELECT image FROM Image image  WHERE id_publication = :pubid");
		query.setParameter("pubid", pubid);
		List<Image> img = query.getResultList();

	    return img.get(0);  
	}
	//les publications user//
	public List<Publication> findAllUserPublication(Long userid){
		Query query  = entityManager.createQuery("SELECT publication FROM Publication publication WHERE id_user = :idUser");
		 query.setParameter("idUser", userid);
		 List<Publication> publications = query.getResultList();
		 return publications ;
	}

}
