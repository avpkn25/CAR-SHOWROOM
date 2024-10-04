package com.klef.ep.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact_table")
public class Contact implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="name",length=30)
	private String name;
	@Id
	@Column(name="email",length=50)
	private String email;
	@Column(name="mobile",length=20)
	private String mobile;
	@Column(name="comments")
	private String comments;
	@Column(name="invalidcheck",nullable = false)
	private boolean invalidcheck;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public boolean isInvalidcheck() {
		return invalidcheck;
	}
	public void setInvalidcheck(boolean invalidcheck) {
		this.invalidcheck = invalidcheck;
	}
}
