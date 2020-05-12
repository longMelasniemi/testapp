package com.example.Note.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Category", schema = "public", catalog = "postgres")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id", nullable = false, updatable = false)
	private Long category_id;

	@Column(name = "name", nullable = false, updatable = true)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Note> Notes;

	public Category() {

	}

	public Category( String name) {
		super();
		this.name = name;
	
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Note> getNotes() {
		return Notes;
	}

	public void setNotes(List<Note> Notes) {
		this.Notes = Notes;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", name=" + name + "]";
	}


}
