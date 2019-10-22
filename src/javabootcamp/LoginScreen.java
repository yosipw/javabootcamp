package javabootcamp;

import java.util.Map;

public class LoginScreen implements Screen {
	private String accountNumber = "";
	private String pin = "";
	private boolean isComplete = false;

	@Override
	public void printString() {
		if (accountNumber.isEmpty()) {
			System.out.println("Enter Account Number: ");
		} else if (pin.isEmpty()) {
			System.out.println("Enter Pin: ");
		}
	}
	
	private boolean validate(String value) {
		if (accountNumber.isEmpty()) {
			if (value.length() != 6){
				System.out.println("Account Number should have 6 digits length");
				return false;
			} 
			else if (!value.matches("[0-9]+")) {
				System.out.println("Account Number should only contains numbers");
				return false;
			} 
		} else if (pin.isEmpty()) {
			if (value.length() != 6){
				System.out.println("Pin should have 6 digits length");
				return false;
			} 
			else if (!value.matches("[0-9]+")) {
				System.out.println("Pin should only contains numbers");
				return false;
			} 
		}
		
		return true;
	}
	
	private boolean validateLogin(){
		 if (!accountNumber.isEmpty() && !pin.isEmpty()) {
				Map<String, Account> accountList = Accounts.getAccounts();
				Account acc = accountList.get(accountNumber);
				
				if(acc == null || !pin.equals(acc.getPin())) {
					System.out.println("Invalid Account Number/PIN");
					accountNumber = "";
					pin = "";
					return false;
				}

				Accounts.setActiveUser(acc);
			}
		 return true;
	}

	@Override
	public void inputString(String value) {
		
		boolean valid = validate(value);
		
		if (!valid){
			return;
		}
		
		if (accountNumber.isEmpty()) {
			accountNumber = value;
		} else if (pin.isEmpty()) {
			pin = value;
		} 
		
		valid = validateLogin();
		
		if (!accountNumber.isEmpty() && !pin.isEmpty() && valid){
			isComplete = true;
			Account acc = Accounts.getActiveUser();
			
			System.out.println("active user account :" + acc.getAccountNumber());
			System.out.println("active user name :" + acc.getName());
			System.out.println("balance :$" + acc.getBalance());
		}
	}

	@Override
	public void runPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ScreenName changePage() {

		if(isComplete) {
			return ScreenName.TRANSACTION;
		}
		return null;
		
	}

}
