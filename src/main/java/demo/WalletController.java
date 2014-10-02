package demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class WalletController {
	
	 Map<Integer, UserDetail> empData = new HashMap<Integer, UserDetail>();
	 WalletManager wManager = new WalletManager();
	@RequestMapping(value = "/users"  , method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserDetail createUser(@RequestBody User json){
		int random_id = (int)(Math.random()*100000);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		json.setUser_id(random_id);
		json.setCreated_at(dateFormat.format(currentDate));
		json.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(json.getUser_id(), json);
		
		return new UserDetail(json.getEmail(),json.getPassword(),"u-"+json.getUser_id(),json.getCreated_at());
	}
	
	@RequestMapping(value = "/users/{userId}"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserDetail viewUser(@PathVariable String userId){
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		return new UserDetail(existingUser.getEmail(),existingUser.getPassword(),"u-"+existingUser.getUser_id(),existingUser.getCreated_at());
	}
	
	@RequestMapping(value = "/users/{userId}"  , method = RequestMethod.PUT, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserDetail updateUser(@PathVariable String userId,@RequestBody User json){
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		wManager.deleteUser(uId);
		
		existingUser.setPassword(json.getPassword());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(uId, existingUser);
		
		return new UserDetail(existingUser.getEmail(),existingUser.getPassword(),"u-"+existingUser.getUser_id(),existingUser.getCreated_at());
		
	}
	
	@RequestMapping(value = "/users/{userId}/idcards"  , method = RequestMethod.POST, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public CardDetail createIDCard(@PathVariable String userId,@RequestBody IDCard idcard){
		
		int random_id = (int)(Math.random()*100000);
		idcard.setCard_id(random_id);
		
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		wManager.deleteUser(uId);
		
		existingUser.setIDCard(random_id, idcard);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(uId, existingUser);
		
		return new CardDetail("c-"+idcard.getCard_id(),idcard.getCard_name(),idcard.getCard_number(),idcard.getExpiration_date());
	}
	
	@RequestMapping(value = "/users/{userId}/idcards"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ArrayList<CardDetail> listAllCardId(@PathVariable String userId){
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		ArrayList<CardDetail> cardList = new ArrayList<CardDetail>();
		HashMap<Integer, IDCard> userCards = existingUser.getIDCard();
		for(Object card : userCards.values()){
			IDCard icard = new IDCard();
			icard = (IDCard)card;
			cardList.add(new CardDetail("c-"+icard.getCard_id(), icard.getCard_name(), icard.getCard_number(), icard.getExpiration_date()));
		}
		
		return cardList;
	}
	
	@RequestMapping(value = "/users/{userId}/idcards/{card_id}"  , method = RequestMethod.DELETE, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void deleteIDCard(@PathVariable String userId,@PathVariable String card_id){
		
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		wManager.deleteUser(uId);
		
		String parseCard[] = card_id.split("-");
		int cId = Integer.parseInt(parseCard[1]);
		existingUser.deleteICard(cId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(uId, existingUser);
		
	}
	
	@RequestMapping(value = "/users/{userId}/weblogins"  , method = RequestMethod.POST, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public WebLogin createWebLogin(@PathVariable String userId,@RequestBody WebLogin webLogin){
		
		int random_id = (int)(Math.random()*100000);
		webLogin.setLogin_id(random_id);
		
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		wManager.deleteUser(uId);
		
		existingUser.setWebLogins(random_id, webLogin);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(uId, existingUser);
		
		return webLogin;
		
	}
	
	@RequestMapping(value = "/users/{userId}/weblogins"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ArrayList<WebLoginDetail> listAllWebLogins(@PathVariable String userId){
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		ArrayList<WebLoginDetail> loginList = new ArrayList<WebLoginDetail>();
		HashMap<Integer, WebLogin> userLogins = existingUser.getWebLogins();
		for(Object card : userLogins.values()){
			WebLogin wLogin = new WebLogin();
			wLogin = (WebLogin)card;
			loginList.add(new WebLoginDetail("l-"+wLogin.getLogin_id(), wLogin.getUrl(), wLogin.getLogin(), wLogin.getPassword()));
		}
		return loginList;
	}
	
	@RequestMapping(value = "/users/{userId}/weblogins/{login_id}"  , method = RequestMethod.DELETE, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void deleteLogin(@PathVariable String userId,@PathVariable String login_id){
		
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		wManager.deleteUser(uId);
		
		String parseLoginId[] = login_id.split("-");
		int lId = Integer.parseInt(parseLoginId[1]);
		existingUser.deleteLoginId(lId);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(uId, existingUser);
		
	}
	
	@RequestMapping(value = "/users/{userId}/bankaccounts"  , method = RequestMethod.POST, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public BankAccount createWebLogin(@PathVariable String userId,@RequestBody BankAccount bankAccount){
		
		int random_id = (int)(Math.random()*100000);
		bankAccount.setBa_id("b-"+random_id);
		
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		wManager.deleteUser(uId);
		
		existingUser.setBankAccount(random_id, bankAccount);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(uId, existingUser);
		
		return bankAccount;
		
	}
	
	@RequestMapping(value = "/users/{userId}/bankaccounts"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public ArrayList<BankAccount> listAllBankAccounts(@PathVariable String userId){
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		ArrayList<BankAccount> bankAccountList = new ArrayList<BankAccount>();
		HashMap<Integer, BankAccount> userAccounts = existingUser.getBankAccount();
		for(Object acc : userAccounts.values()){
			BankAccount account = new BankAccount();
			account = (BankAccount)acc;
			bankAccountList.add(account);
		}
		return bankAccountList;
	}
	
	@RequestMapping(value = "/users/{userId}/bankaccounts/{ba_id}"  , method = RequestMethod.DELETE, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void deleteBankAccount(@PathVariable String userId,@PathVariable String ba_id){
		
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = wManager.getUser(uId);
		wManager.deleteUser(uId);
		
		String parseAccountID[] = ba_id.split("-");
		int ba_ID = Integer.parseInt(parseAccountID[1]);
		existingUser.deleteBankAccount(ba_ID);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		
		wManager.addUser(uId, existingUser);
		
	}
	
}
