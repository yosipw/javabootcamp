package javabootcamp;

import java.util.HashMap;
import java.util.Map;

public class Accounts {
	
	private static Map<String, Account> accountList = null;
	
	private static Account activeAccount = null;
	
	private Accounts(){
		accountList = new HashMap<>();
		accountList.put("111222", new Account("John Doe", "111222", 100, "111222"));
		accountList.put("112244", new Account("Jane Doe", "932012", 30, "112244"));
	}
	
	public static Map<String, Account> getAccounts(){
		if(accountList == null) {
			new Accounts();
		}
		return accountList;
	}
	
	public Account accountByAccountNumber(String key){
		Account res = accountList.get(key);
		if(res != null) {
			return res;
		}
		return null;
	}
	
	public static void setActiveUser(Account acc){
		activeAccount = acc;
	}
	
	public static Account getActiveUser(){
		return activeAccount;
	}

}
