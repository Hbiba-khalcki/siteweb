package com.projet.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Publication {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String title;
	private String date;
	private String description ;
	private String category;
	private boolean confirm;
	private boolean cancel;
	@ManyToOne
	@JoinColumn(name = "idUser")
	private User idUser;
	
	
	
	
	

	public User getIdUser() {
		return idUser;
	}
	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	public boolean isCancel() {
		return cancel;
	}
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Publication(String title, String date, String description, String category) {
		super();
		this.title = title;
		this.date = date;
		this.description = description;
		this.category = category;
		this.confirm= false;
		this.cancel= false;
	}
	public Publication() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}