package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String genre;
	
	@NotBlank
	private String publishedYear;
	
	public Movie() {
		this(-1L, "N/A", "N/A", "N/A", "0000");
	}
	

	@Override
	public String toString() {
		return "Movie [id=" + id + ", name=" + name + ", description=" + description + ", genre=" + genre
				+ ", publishedYear=" + publishedYear + "]";
	}


	public Movie(Long id, @NotBlank String name, @NotBlank String description, @NotBlank String genre,
			@NotBlank String publishedYear) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.publishedYear = publishedYear;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublishedYear() {
		return publishedYear;
	}

	public void setPublishedYear(String publishedYear) {
		this.publishedYear = publishedYear;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	


	

}
