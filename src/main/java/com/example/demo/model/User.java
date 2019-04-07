package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="user")
public class User {
	
	
	@Id
	private long id;
	private String userName;
	private String emailId;
	private String password;
	//private ArrayList<Note> notes;
	private HashMap<Integer, Note> notes;
	
	public User() {
		this.notes=new HashMap<Integer,Note>();
	}
	
	public long getId() {
		return id;
		
	}
	public void setId(long id) {	
		this.id = id;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public HashMap<Integer,Note> getNotes() {
		return notes;
	}
	public void setNotes(HashMap<Integer,Note> notes) {
		this.notes = notes;
	}
	
}
