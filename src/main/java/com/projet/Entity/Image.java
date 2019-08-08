package com.projet.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nom;
	private String extension;
	private String url;
	@ManyToOne
	@JoinColumn(name = "idPublication")
	private Publication idPublication;

	public long getId() {
		return id;
	}

	public String getIdString() {
		return String.valueOf(id);
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Publication getIdPublication() {
		return idPublication;
	}

	public void setIdPublication(Publication idPublication) {
		this.idPublication = idPublication;
	}

	public Image(String nom, String extension, String url, Publication idPublication) {
		super();
		this.nom = nom;
		this.extension = extension;
		this.url = url;
		this.idPublication = idPublication;
	}

	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}

}
