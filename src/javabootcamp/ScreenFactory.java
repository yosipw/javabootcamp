package javabootcamp;

public class ScreenFactory {
	
	public Screen getScreen(ScreenName screenName) {
		Screen screen = null;
		switch(screenName) {
		
		case TRANSACTION: 
			screen = new TransactionScreen();
			break;
		case LOGIN:
			screen = new LoginScreen();
			break;
		case WITHDRAW:
			screen = new WithdrawScreen();
			break;
		case FUND_TRANSFER:
			screen = new FundTransferScreen();
			break;
		default:
			screen = null;
			System.out.println("Page not found");
		}

		return screen;
	}

}
