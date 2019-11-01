package bootcamp.screen;

/**
 * @author Yosua_S
 *
 */
public class FactoryScreen {
	
	/**
	 * @param screenName
	 * @return
	 * populate the screen based on the EnumScreen name
	 * Print a message when the page not found
	 */
	public InterfaceScreenAction setCurrentScreen(EnumScreen screenName) {
		InterfaceScreenAction screen = null;
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
