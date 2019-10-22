package javabootcamp;

public class TransactionScreen implements Screen {
	private ScreenName toPage;

	@Override
	public void printString() {
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Exit");
		System.out.println("Please choose option[3]:");
	}

	@Override
	public void inputString(String page) {
		switch(page){
		case "1":
			toPage = ScreenName.WITHDRAW;
			break;
		case "2":
			toPage = ScreenName.FUND_TRANSFER;
			break;
		case "3":
			toPage = ScreenName.LOGIN;
			break;
		}
	}

	@Override
	public ScreenName changePage() {
		return toPage;
	}

	@Override
	public void runPage() {
		// TODO Auto-generated method stub
		
	}

}
