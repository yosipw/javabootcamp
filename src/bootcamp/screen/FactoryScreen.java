package screen;

public class FactoryScreen {
	
	public InterfaceScreen getScreen(EnumScreen screenName) {
		InterfaceScreen screen = null;
		switch(screenName) {
		
		case TRANSACTION: 
			screen = new ScreenTransaction();
			break;
		case LOGIN:
			screen = new ScreenLogin();
			break;
		case WITHDRAW:
			screen = new ScreenWithdraw();
			break;
		case FUND_TRANSFER:
			screen = new ScreenFundTransfer();
			break;
		case SUMMARY:
			screen = new ScreenSummary();
			break;
		case OTHER_WITHDRAW:
			screen = new ScreenOtherWithdraw();
			break;
		default:
			screen = null;
			System.out.println("Page not found");
		}

		return screen;
	}

}
