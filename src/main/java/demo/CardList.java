package demo;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class CardList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CardDetail> cardDetails = new ArrayList<CardDetail>();
	
	public CardList(ArrayList<CardDetail> cardDetails){
		this.cardDetails = cardDetails;
	}
	
	public ArrayList<CardDetail> getCardDetails() {
		return cardDetails;
	}
	public void setCardDetails(ArrayList<CardDetail> cardDetails) {
		this.cardDetails = cardDetails;
	}
	
	
	
}
