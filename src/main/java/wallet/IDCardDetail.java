package wallet;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IDCardDetail implements Serializable {


	private static final long serialVersionUID = 1L;
	private String card_id;
	private String card_name;
	private String card_number;
	private String expiration_date;
	
	public IDCardDetail(String card_id,String card_name,String card_number, String expiration_date){
		this.card_id = card_id;
		this.card_name = card_name;
		this.card_number = card_number;
		this.expiration_date = expiration_date;
	}
	
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(String expiration_date) {
		this.expiration_date = expiration_date;
	}

}
