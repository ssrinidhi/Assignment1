package demo;

import java.util.HashMap;

public class WalletManager {
	
	HashMap<Integer,User> userMap = new HashMap<Integer,User>();
	
	public void addUser(int userId, User user){
		userMap.put(new Integer(userId), user);
	}
	
	public User getUser(int userId){
		return userMap.get(userId);
	}
	
	public void deleteUser(int userId){
		userMap.remove(userId);
	}

}
