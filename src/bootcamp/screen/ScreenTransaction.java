package screen;

public class ScreenTransaction extends AbstractScreen {

	@Override
	public void printMessage() {
		System.out.println("--------------------------");
		System.out.println("1. Withdraw");
		System.out.println("2. Fund Transfer");
		System.out.println("3. Exit");
		System.out.println("Please choose option[3]:");
	}

	@Override
	public void readInput(String page) {
		switch(page){
		case "":
			toPage = EnumScreen.LOGIN;
			break;
		case "1":
			toPage = EnumScreen.WITHDRAW;
			break;
		case "2":
			toPage = EnumScreen.FUND_TRANSFER;
			break;
		case "3":
			toPage = EnumScreen.LOGIN;
			break;
		default:
			break;
		}
	}

}
