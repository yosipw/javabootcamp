package screen;

import java.time.LocalDate;

import entity.Account;
import entitydata.AccountDAO;

public class ScreenSummary extends AbstractScreen {
	
	@Override
	public void printMessage() {
		
		Account account = AccountDAO.getActiveUser();

		System.out.println("--------------------------");
		System.out.println("Summary");
		System.out.println("Date : " + LocalDate.now());
		System.out.println("Withdraw : $" + account.getLastTransaction().getAmount());
		System.out.println("Balance : $" + account.getBalance());
		System.out.println("");
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		System.out.println("Choose option[2]:");
	}

	@Override
	public void readInput(String value) {
		
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

	}

}
