package bootcamp.screen;

import java.time.LocalDate;

import bootcamp.dao.AccountDAO;
import bootcamp.entity.Account;
import bootcamp.entity.Transaction;

/**
 * @author Yosua_S
 *
 */
public class ScreenFundTransfer extends AbstractScreen {
	enum Step {
		DESTINATION,AMOUNT,REFERENCE,SUMMARY,CONFIRM
	}
	private String destinationAccount;
	private String credit;
	private String referenceNumber;
	private Step step = Step.DESTINATION;

	@Override
	public void printMessage() {
		switch(step) {
		case DESTINATION:
			System.out.println("--------------------------");
			System.out.println("Please enter destination account and press enter to continue or");
			System.out.println("press enter to go back to Transaction:");
			break;
		case AMOUNT:
			System.out.println("--------------------------");
			System.out.println("Please enter transfer amount and");
			System.out.println("press enter to continue or");
			System.out.println("press enter to go back to Transaction:");
			break;
		case REFERENCE:
			System.out.println("--------------------------");
			System.out.println("Reference Number: " + referenceNumber);
			System.out.println("press enter to continue");
			break;
		case CONFIRM:
			System.out.println("--------------------------");
			System.out.println("Transfer Confirmation");
			System.out.println("Destination Account : " + destinationAccount);
			System.out.println("Transfer Amount     : $" + credit);
			System.out.println("Reference Number    : " + referenceNumber);
			System.out.println("1. Confirm Trx");
			System.out.println("2. Cancel Trx");
			System.out.println("Choose option[2]:");
			break;
		case SUMMARY:
			System.out.println("--------------------------");
			System.out.println("Fund Transfer Summary");
			System.out.println("Destination Account : "+ destinationAccount);
			System.out.println("Transfer Amount     : $" + credit);
			System.out.println("Reference Number    : " + referenceNumber);
			System.out.println("1. Transaction");
			System.out.println("2. Exit");
			System.out.println("Choose option[2]:");
			break;
		default:
			break;
		}
	}

	
	@Override
	public void readInput(String value) {
		boolean isValid = false;
		switch(step) {
		case DESTINATION:
			if(value.equals("")){
				toPage = EnumScreen.TRANSACTION;
				break;
			}
			isValid = validateAccount(value);
			if(isValid) {
				destinationAccount = value;
				step = Step.AMOUNT;
			}
			break;
		case AMOUNT:
			if(value.equals("")){
				toPage = EnumScreen.TRANSACTION;
				break;
			}
			isValid = validateCredit(value);
			if(isValid) {
				credit = value;
				referenceNumber = generateRandomNumbers();
				step = Step.REFERENCE;
			}
			break;
		case REFERENCE:
			if("".equalsIgnoreCase(value)) {
				step = Step.CONFIRM;
			}
			break;
		case CONFIRM:
			
			switch(value){
			case "1":
				int creditVal = Integer.parseInt(credit);
				Account acc = AccountDAO.getActiveUser();
				int balance = acc.getBalance();
				balance-= creditVal;
				acc.setBalance(balance);
				acc.addTransaction(new Transaction(creditVal, LocalDate.now()));
				AccountDAO.setActiveUser(acc);
				AccountDAO.saveAccount(acc);

				Account destAcc = AccountDAO.accountByAccountNumber(destinationAccount);
				balance = destAcc.getBalance();
				balance+= creditVal;
				destAcc.setBalance(balance);
				AccountDAO.saveAccount(destAcc);

				step = Step.SUMMARY;
				break;
			case "":
			case "2":
				credit = "";
				referenceNumber = "";
				destinationAccount = "";
				step = Step.DESTINATION;
				break;
			default:
				break;
			}
			break;
		case SUMMARY:
			switch(value){
			case "":
				toPage = EnumScreen.LOGIN;
				break;
			case "1":
				toPage = EnumScreen.TRANSACTION;
				break;
			case "2":
				toPage = EnumScreen.LOGIN;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * @return
	 * Generate random number as the reference number
	 */
	private String generateRandomNumbers(){
		int n = 6;
        String AlphaNumericString = "0123456789"; 

        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
        return sb.toString(); 
	}
	
	/**
	 * @param destination
	 * @return
	 * Validate the account when it is not a number or
	 * the targeted account was not exist
	 */
	private boolean validateAccount(String destination) {
		try{
			Integer.parseInt(destination);
		} catch(NumberFormatException e){
			System.out.println("--------------------------");
			System.out.println("Invalid account");
			return false;
		}
		
		Account destAcc = AccountDAO.accountByAccountNumber(destination);
		if(destAcc == null) {
			System.out.println("--------------------------");
			System.out.println("Invalid account");
			return false;
		}
		return true;
	}
	
	/**
	 * @param credit
	 * @return
	 * Validate credit input when it is not a number,
	 * more than 100,
	 * less than 0,
	 * or the balance is less than the credit amount
	 */
	private boolean validateCredit(String credit){
		int creditVal = 0;
		try{
			creditVal = Integer.parseInt(credit);
		} catch(NumberFormatException e){
			System.out.println("--------------------------");
			System.out.println("Invalid amount");
			return false;
		}
		
		if(creditVal>1000){
			System.out.println("--------------------------");
			System.out.println("Maximum amount to withdraw is $1000");
			return false;
		}
		if(creditVal<1) {
			System.out.println("--------------------------");
			System.out.println("Minimum amount to withdraw is $1");
			return false;
		}
		
		Account acc = AccountDAO.getActiveUser();
		if(acc.getBalance() < creditVal){
			System.out.println("--------------------------");
			System.out.println("Insufficient balance $" + creditVal);
			return false;
		}
		return true;
	}
}
