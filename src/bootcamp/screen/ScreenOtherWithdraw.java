package screen;

import java.time.LocalDate;

import entity.Account;
import entity.Transaction;
import entitydata.AccountDAO;

public class ScreenOtherWithdraw extends AbstractScreen {

	@Override
	public void printMessage() {
		System.out.println("--------------------------");
		System.out.println("Other Withdraw");
		System.out.println("Enter amount to withdraw:");
	}

	@Override
	public void readInput(String value) {
		int credit = 0;
		try{
			credit = Integer.parseInt(value);
		}catch(NumberFormatException e){
			System.out.println("--------------------------");
			System.out.println("Invalid amount");
			return;
		}
		
		if(credit > 1000){
			System.out.println("--------------------------");
			System.out.println("Maximum amount to withdraw is $1000");
			return;
		}
		
		if(credit%10 > 0){
			System.out.println("--------------------------");
			System.out.println("Invalid amount");
			return;
		}
		
		Account acc = AccountDAO.getActiveUser();
		int balance = acc.getBalance();
		if (balance < credit) {
			System.out.println("--------------------------");
			System.out.println("Insufficient balance $" + credit);
			return;
		}
		balance-= credit;
		acc.setBalance(balance);
		acc.addTransaction(new Transaction(credit, LocalDate.now()));
		AccountDAO.setActiveUser(acc);
		toPage = EnumScreen.SUMMARY;
	}

}
