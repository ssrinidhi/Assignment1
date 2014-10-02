package wallet;

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
public class BaseController {
	
	 ManageUserMap userMap = new ManageUserMap();
	@RequestMapping(value = "/api/v1/users"  , method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserObj createUser(@RequestBody User usr){
		int rand = (int)(Math.random()*100000);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		usr.setCreated_at(dateFormat.format(date));
		usr.setUpdated_at(dateFormat.format(date));
		usr.setUser_id(rand);
		userMap.addUser(usr.getUser_id(), usr);
		return new UserObj(usr.getEmail(),usr.getPassword(),"u-"+usr.getUser_id(),usr.getCreated_at());
	}

	@RequestMapping(value = "/api/v1/users/{userId}"  , method = RequestMethod.PUT, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public UserObj updateUser(@PathVariable String userId,@RequestBody User user){
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = userMap.getUser(uId);
		userMap.deleteUser(uId);
		existingUser.setPassword(user.getPassword());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		userMap.addUser(uId, existingUser);
		return new UserObj(existingUser.getEmail(),existingUser.getPassword(),"u-"+existingUser.getUser_id(),existingUser.getCreated_at());
		
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public UserObj viewUser(@PathVariable String userId){
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existUser = new User();
		existUser = userMap.getUser(usrId);
		return new UserObj(existUser.getEmail(),existUser.getPassword(),"u-"+existUser.getUser_id(),existUser.getCreated_at());
	}
	
	
	@RequestMapping(value = "/api/v1/users/{userId}/idcards"  , method = RequestMethod.POST, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public IDCardDetail createIDCard(@PathVariable String userId,@RequestBody IDCard idcard){
		
		int randid = (int)(Math.random()*100000);
		idcard.setCard_id(randid);
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = userMap.getUser(usrId);
		userMap.deleteUser(usrId);
		existingUser.setIDCard(randid, idcard);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		existingUser.setUpdated_at(dateFormat.format(date));
		userMap.addUser(usrId, existingUser);
		return new IDCardDetail("c-"+idcard.getCard_id(),idcard.getCard_name(),idcard.getCard_number(),idcard.getExpiration_date());
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/idcards"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ArrayList<IDCardDetail> listAllCardId(@PathVariable String userId){
		String parse[] = userId.split("-");
		int uId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = userMap.getUser(uId);
		ArrayList<IDCardDetail> cardList = new ArrayList<IDCardDetail>();
		HashMap<Integer, IDCard> userCards = existingUser.getIDCard();
		for(Object card : userCards.values()){
			IDCard icard = new IDCard();
			icard = (IDCard)card;
			cardList.add(new IDCardDetail("c-"+icard.getCard_id(), icard.getCard_name(), icard.getCard_number(), icard.getExpiration_date()));
		}
		
		return cardList;
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/idcards/{card_id}"  , method = RequestMethod.DELETE, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteIDCard(@PathVariable String userId,@PathVariable String card_id){
		
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existUser = new User();
		existUser = userMap.getUser(usrId);
		userMap.deleteUser(usrId);
		String parseCard[] = card_id.split("-");
		int cardId = Integer.parseInt(parseCard[1]);
		existUser.deleteICard(cardId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		existUser.setUpdated_at(dateFormat.format(date));
		userMap.addUser(usrId, existUser);
		
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/weblogins"  , method = RequestMethod.POST, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public WebLogin createWebLogin(@PathVariable String userId,@RequestBody WebLogin webLogin){
		
		int random_id = (int)(Math.random()*100000);
		webLogin.setLogin_id(random_id);
		
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = userMap.getUser(usrId);
		userMap.deleteUser(usrId);
		existingUser.setWebLogins(random_id, webLogin);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date currentDate = new Date();
		existingUser.setUpdated_at(dateFormat.format(currentDate));
		userMap.addUser(usrId, existingUser);
		return webLogin;
		
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/weblogins"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ArrayList<WebLoginObj> listAllWebLogins(@PathVariable String userId){
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existUser = new User();
		existUser = userMap.getUser(usrId);
		ArrayList<WebLoginObj> loginList = new ArrayList<WebLoginObj>();
		HashMap<Integer, WebLogin> userLogins = existUser.getWebLogins();
		for(Object card : userLogins.values()){
			WebLogin wLogin = new WebLogin();
			wLogin = (WebLogin)card;
			loginList.add(new WebLoginObj("l-"+wLogin.getLogin_id(), wLogin.getUrl(), wLogin.getLogin(), wLogin.getPassword()));
		}
		return loginList;
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/weblogins/{login_id}"  , method = RequestMethod.DELETE, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteLogin(@PathVariable String userId,@PathVariable String login_id){
		
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existUser = new User();
		existUser = userMap.getUser(usrId);
		userMap.deleteUser(usrId);
		String parseLoginId[] = login_id.split("-");
		int logId = Integer.parseInt(parseLoginId[1]);
		existUser.deleteLoginId(logId);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		existUser.setUpdated_at(dateFormat.format(date));
		userMap.addUser(usrId, existUser);
		
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/bankaccounts"  , method = RequestMethod.POST, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public BankAccount createBankAccount(@PathVariable String userId,@RequestBody BankAccount bankAccount){
		
		int randid = (int)(Math.random()*100000);
		bankAccount.setBa_id("b-"+randid);
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = userMap.getUser(usrId);
		userMap.deleteUser(usrId);
		existingUser.setBankAccount(randid, bankAccount);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		existingUser.setUpdated_at(dateFormat.format(date));
		userMap.addUser(usrId, existingUser);
		return bankAccount;
		
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/bankaccounts"  , method = RequestMethod.GET, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ArrayList<BankAccount> listAllBankAccounts(@PathVariable String userId){
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existUser = new User();
		existUser = userMap.getUser(usrId);
		ArrayList<BankAccount> bankAccountList = new ArrayList<BankAccount>();
		HashMap<Integer, BankAccount> userAccounts = existUser.getBankAccount();
		for(Object acc : userAccounts.values()){
			BankAccount accnt = new BankAccount();
			accnt = (BankAccount)acc;
			bankAccountList.add(accnt);
		}
		return bankAccountList;
	}
	
	@RequestMapping(value = "/api/v1/users/{userId}/bankaccounts/{ba_id}"  , method = RequestMethod.DELETE, headers ="Accept=application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public void deleteBankAccount(@PathVariable String userId,@PathVariable String ba_id){
		
		String parse[] = userId.split("-");
		int usrId = Integer.parseInt(parse[1]);
		User existingUser = new User();
		existingUser = userMap.getUser(usrId);
		userMap.deleteUser(usrId);
		String parseAccountID[] = ba_id.split("-");
		int ba_ID = Integer.parseInt(parseAccountID[1]);
		existingUser.deleteBankAccount(ba_ID);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		existingUser.setUpdated_at(dateFormat.format(date));
		userMap.addUser(usrId, existingUser);
		
	}
	
}
