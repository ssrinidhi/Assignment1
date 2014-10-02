package wallet;

import java.io.Serializable;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IDCard implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int card_id;
	private String card_name;
	private String card_number;
	private String expiration_date;
	
	
	public int getCard_id() {
		return this.card_id;
	}
	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	
	@JsonSerialize
	public String getCard_name() {
		return this.card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	
	@JsonSerialize
	public String getCard_number() {
		return this.card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	
	@JsonSerialize
	public String getExpiration_date() {
		return this.expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

	
}
