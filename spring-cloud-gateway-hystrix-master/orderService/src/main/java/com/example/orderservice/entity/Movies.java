package com.example.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "releaseYear")
	private String releaseYear;

	@Column(name = "story")
	private String story;

	@Column(name = "base64Img")
	private String base64Img;

	@Column(name = "languageId")
	private String languageId;

	@Column(name = "genreId")
	private String  genreId;

	@Column(name = "createdBy")
	private String createdBy;

	@Column(name = "active")
	private String active;

	@Column(name = "createdTimestamp")
	private Date createdTimestamp;

	@Column(name = "lastUpdtTimestamp")
	private Date lastUpdtTimestamp;

	@Transient
	private String genre;

	@Transient
	private String language;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	@JsonUnwrapped
	private Ratings rating;

	@Transient
	private List<Reviews> reviews;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String  getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String  releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public String getBase64Img() {
		return base64Img;
	}

	public void setBase64Img(String base64Img) {
		this.base64Img = base64Img;
	}

	public String  getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String  languageId) {
		this.languageId = languageId;
	}

	public String  getGenreId() {
		return genreId;
	}

	public void setGenreId(String  genreId) {
		this.genreId = genreId;
	}

	public String  getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String  createdBy) {
		this.createdBy = createdBy;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getLastUpdtTimestamp() {
		return lastUpdtTimestamp;
	}

	public void setLastUpdtTimestamp(Date lastUpdtTimestamp) {
		this.lastUpdtTimestamp = lastUpdtTimestamp;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Ratings getRating() {
		return rating;
	}

	public void setRating(Ratings rating) {
		this.rating = rating;
	}

	public List<Reviews> getReviews() {
		return reviews;
	}

	public void setReviews(List<Reviews> reviews) {
		this.reviews = reviews;
	}

}
