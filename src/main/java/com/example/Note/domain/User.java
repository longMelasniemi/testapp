package com.example.Note.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "User", schema = "public", catalog = "postgres")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userid", nullable = false, updatable = false)
	private Long userid;

	@Column(name = "username", nullable = false, updatable = true)
	private String username;

	@Column(name = "password", nullable = false, updatable = true)
	private String password;

	@Column(name = "role", nullable = false, updatable = true)
	private String role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Note> Notes;

	public List<Note> getNotes() {
		return Notes;
	}

	public void setNotes(List<Note> Notes) {
		this.Notes = Notes;
	}

	public User() {

	}

	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}


}
