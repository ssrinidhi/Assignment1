package demo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.DateSerializer;

public class UserDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String password;
	private String user_id;
	private String created_at;
	
	public UserDetail(String email, String password, String user_id, String created_at){
		this.setEmail(email);
		this.setPassword(password);
		this.user_id = user_id;
		this.created_at = created_at;
	}

	public String getEmail() {
		return email;
	}
	@JsonSerialize
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	@JsonSerialize
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
