package com.myapp.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GruberReqData {

	private String email;
	private String password;

	/**
	 * 
	 */
	public GruberReqData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param email
	 * @param password
	 */
	public GruberReqData(String email, String password) {
		super();
		this.setEmail(email);
		this.setPassword(password);
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
