package demo;

import java.io.Serializable;
import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_id;
	private String email;
	private String password;
	private String name;
	private String created_at;
	private String updated_at;
	private HashMap<Integer,BankAccount> bankAccount = new HashMap<Integer,BankAccount>();
	private HashMap<Integer,IDCard> iDCard = new HashMap<Integer,IDCard>();
	private HashMap<Integer,WebLogin> webLogins = new HashMap<Integer,WebLogin>();
	
	
	public void setUser_id(int user_id){
		this.user_id = user_id;
	}
	@JsonSerialize
	public void setEmail(String email){
		this.email = email;
	}
	@JsonSerialize
	public void setPassword(String password){
		this.password = password;
	}
	@JsonSerialize
	public void setName(String name){
		this.name = name;
	}
	public void setCreated_at(String date){
		this.created_at = date;
	}
	public void setUpdated_at(String date){
		this.updated_at = date;
	}
	public void setBankAccount(int bankId,BankAccount bankAccount){
		this.bankAccount.put(new Integer(bankId), bankAccount);
	}
	public void setIDCard(int iCardID,IDCard iDCard){
		this.iDCard.put(new Integer(iCardID), iDCard);
	}
	
	
	public int getUser_id(){
		return this.user_id;
	}
	public String getEmail(){
		return this.email;
	}
	public String getPassword(){
		return this.password;
	}
	public String getName(){
		return this.name;
	}
	public String getCreated_at(){
		return this.created_at;
	}
	public String getUpdated_at(){
		return this.updated_at;
	}	
	public HashMap<Integer,BankAccount> getBankAccount(){
		return this.bankAccount;
	}
	
	public HashMap<Integer,IDCard> getIDCard(){
		return this.iDCard;
	}
	public void deleteBankAccount(int ba_id){
		this.bankAccount.remove(ba_id);
	}
	
	public void deleteICard(int iCard){
		this.iDCard.remove(iCard);
	}
	
	public HashMap<Integer,WebLogin> getWebLogins() {
		return this.webLogins;
	}
	public void setWebLogins(int login_id,WebLogin webLogins) {
		this.webLogins.put(login_id, webLogins);
	}
	public void deleteLoginId(int login_id){
		this.webLogins.remove(login_id);
	}
	
}
