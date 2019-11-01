package screen;

import java.util.Map;

import entity.Account;
import entitydata.AccountDAO;

public class ScreenLogin extends AbstractScreen {
	private String accountNumber = "";
	private String pin = "";

	@Override
	public void printMessage() {
		if (accountNumber.isEmpty()) {
			System.out.println("--------------------------");
			System.out.println("Enter Account Number: ");
		} else if (pin.isEmpty()) {
			System.out.println("--------------------------");
			System.out.println("Enter Pin: ");
		}
	}
	
	private boolean validate(String value) {
		if (accountNumber.isEmpty()) {
			if (value.length() != 6){
				System.out.println("--------------------------");
				System.out.println("Account Number should have 6 digits length");
				return false;
			} 
			else if (!value.matches("[0-9]+")) {
				System.out.println("--------------------------");
				System.out.println("Account Number should only contains numbers");
				return false;
			} 
		} else if (pin.isEmpty()) {
			if (value.length() != 6){
				System.out.println("--------------------------");
				System.out.println("Pin should have 6 digits length");
				return false;
			} 
			else if (!value.matches("[0-9]+")) {
				System.out.println("--------------------------");
				System.out.println("Pin should only contains numbers");
				return false;
			} 
		}
		
		return true;
	}
	
	private boolean validateLogin(){
		 if (!accountNumber.isEmpty() && !pin.isEmpty()) {
				Map<String, Account> accountList = AccountDAO.getAccounts();
				Account acc = accountList.get(accountNumber);
				
				if(acc == null || !pin.equals(acc.getPin())) {
					System.out.println("--------------------------");
					System.out.println("Invalid Account Number/PIN");
					accountNumber = "";
					pin = "";
					return false;
				}

				AccountDAO.setActiveUser(acc);
			}
		 return true;
	}

	@Override
	public void readInput(String value) {
		
		boolean valid = validate(value);
		
		if (!valid){
			pin = "";
			accountNumber = "";
			return;
		}
		
		if (accountNumber.isEmpty()) {
			accountNumber = value;
		} else if (pin.isEmpty()) {
			pin = value;
		} 
		
		valid = validateLogin();
		
		if (!accountNumber.isEmpty() && !pin.isEmpty() && valid){
			toPage = EnumScreen.TRANSACTION;
			Account acc = AccountDAO.getActiveUser();

			System.out.println("--------------------------");
			System.out.println("active user account :" + acc.getAccountNumber());
			System.out.println("active user name :" + acc.getName());
			System.out.println("balance :$" + acc.getBalance());
			
			pin = "";
			accountNumber = "";
		}
	}

}
