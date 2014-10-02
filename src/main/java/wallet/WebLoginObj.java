package wallet;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class WebLoginObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login_id;
	private String url;
	private String login;
	private String password;
	
	public WebLoginObj(String login_id,String url,String login, String password){
		this.login_id = login_id;
		this.url = url;
		this.login = login;
		this.password = password;
	}
	
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	
	
	public String getUrl() {
		return this.url;
	}
	@JsonSerialize
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public String getLogin() {
		return this.login;
	}
	@JsonSerialize
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	public String getPassword() {
		return this.password;
	}
	@JsonSerialize
	public void setPassword(String password) {
		this.password = password;
	}
	

}
