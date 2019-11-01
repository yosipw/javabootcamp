package entitydata;

import java.util.HashMap;
import java.util.Map;

import entity.Account;

public class AccountDAO {
	
	private static Map<String, Account> accountList = null;
	
	private static Account activeAccount = null;
	
	private AccountDAO(){
		accountList = new HashMap<>();
		accountList.put("112233", new Account("John Doe", "012108", 100, "112233"));
		accountList.put("112244", new Account("Jane Doe", "932012", 30, "112244"));
	}
	
	public static Map<String, Account> getAccounts(){
		if(accountList == null) {
			new AccountDAO();
		}
		return accountList;
	}
	
	public static Account accountByAccountNumber(String key){
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
