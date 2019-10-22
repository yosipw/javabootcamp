package javabootcamp;

public class WithdrawScreen implements Screen {
	
	private ScreenName toPage;

	@Override
	public ScreenName changePage() {
		if(toPage != null) {
			return toPage;
		}
		return null;
	}

	@Override
	public void runPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printString() {
		System.out.println("1. $10");
		System.out.println("2. $50");
		System.out.println("3. $100");
		System.out.println("4. Other");
		System.out.println("5. Back");
		System.out.println("Please choose option[5]:");
	}

	@Override
	public void inputString(String value) {
		int credit = 0;
		
		switch(value){
		case "1":
			credit = 10;
			break;
		case "2":
			credit = 50;
			break;
		case "3":
			credit = 100;
			break;
		case "4":
			toPage = ScreenName.OTHER_WITHDRAW;
			break;
		case "5":
			toPage = ScreenName.TRANSACTION;
			break;
		}
		
		if(credit>0) {
			Account acc = Accounts.getActiveUser();
			int balance = acc.getBalance();
			balance-= credit;
			
			System.out.println("user :" + acc.getName());
			System.out.println("account number :" + acc.getAccountNumber());
			
			if(balance <0) {
				System.out.println("Insuficient balance $" + acc.getBalance());
				System.out.println("--------------------------");
			} else {
				System.out.println("balance : $" + balance);
				System.out.println("--------------------------");
				acc.setBalance(balance);
				Accounts.setActiveUser(acc);
			}
		}
		
	}


}
