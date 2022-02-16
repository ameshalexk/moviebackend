package com.cognixia.jump.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	
	public List<UsersMovie> getUsermovies() {
		return usermovies;
	}


	public void setUsermovies(List<UsersMovie> usermovies) {
		this.usermovies = usermovies;
	}

	@NotBlank
	private String publishedYear;
	
//	@JsonManagedReference
//	@JsonIgnoreProperties
	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<UsersMovie> usermovies;
	
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
