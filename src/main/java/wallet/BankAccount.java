package wallet;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class BankAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ba_id;
	private String account_name;
	private String routing_number;
	private String account_number;
	
	
	public String getBa_id() {
		return ba_id;
	}
	public void setBa_id(String ba_id) {
		this.ba_id = ba_id;
	}
	
	
	public String getAccount_name() {
		return account_name;
	}
	@JsonSerialize
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	
	
	public String getRouting_number() {
		return routing_number;
	}
	@JsonSerialize
	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}
	
	
	public String getAccount_number() {
		return account_number;
	}
	@JsonSerialize
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	
	

}
