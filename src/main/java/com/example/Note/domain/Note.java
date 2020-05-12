package com.example.Note.domain;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Note", schema = "public", catalog = "postgres")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id", nullable = false, updatable = false)
	private Long note_id;

	@Column(name = "title", nullable = false, updatable = true)
	private String title;

	@Column(name = "content", nullable = false, updatable = true)
	private String content;
	
	
	@Column(name = "date", nullable = false, updatable = false)
	private LocalDate date = LocalDate.now();
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userid")
	private User user;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "category_id")
	private Category category;
	
	public Note() {
		
	}

	public Note( String title, String content, User user,
			Category Category) {
		super();
		this.title = title;
		this.content = content;
		this.user = user;
		this.category = Category;
	}

	public Long getNote_id() {
		return note_id;
	}

	public void setNote_id(Long note_id) {
		this.note_id = note_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@DateTimeFormat (pattern="yyyy-MM-dd") 
	public LocalDate getDate() {
		return date;
	}
	@DateTimeFormat (pattern="yyyy-MM-dd") 
	public void setDate(LocalDate date) {
		this.date = LocalDate.now();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category Category) {
		this.category = Category;
	}

	
}
