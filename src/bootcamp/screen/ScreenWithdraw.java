package bootcamp.screen;

import java.time.LocalDate;

import bootcamp.dao.AccountDAO;
import bootcamp.entity.Account;
import bootcamp.entity.Transaction;

/**
 * @author Yosua_S
 *
 */
public class ScreenWithdraw extends AbstractScreen {

	@Override
	public void printMessage() {
		System.out.println("--------------------------");
		System.out.println("1. $10");
		System.out.println("2. $50");
		System.out.println("3. $100");
		System.out.println("4. Other");
		System.out.println("5. Back");
		System.out.println("Please choose option[5]:");
	}

	@Override
	public void readInput(String value) {
		int credit = 0;
		
		switch(value){
		case "":
			toPage = EnumScreen.LOGIN;
			break;
		case "1":
			credit = 10;
			toPage = EnumScreen.SUMMARY;
			break;
		case "2":
			credit = 50;
			toPage = EnumScreen.SUMMARY;
			break;
		case "3":
			credit = 100;
			toPage = EnumScreen.SUMMARY;
			break;
		case "4":
			toPage = EnumScreen.OTHER_WITHDRAW;
			break;
		case "5":
			toPage = EnumScreen.TRANSACTION;
			break;
		default:
			break;
		}
		
		if(credit>0) {
			Account acc = AccountDAO.getActiveUser();
			int balance = acc.getBalance();
			balance-= credit;

			
			if(balance <0) {
				System.out.println("--------------------------");
				System.out.println("Insuficient balance $" + credit);
				acc.addTransaction(new Transaction(0, LocalDate.now()));
				toPage = null;
			} else {
				System.out.println("--------------------------");
				System.out.println("balance : $" + balance);
				acc.setBalance(balance);
				acc.addTransaction(new Transaction(credit, LocalDate.now()));
				AccountDAO.saveAccount(acc);
			}
			AccountDAO.setActiveUser(acc);
		}
	}

}
