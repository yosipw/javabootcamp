package javabootcamp;

public class SummaryScreen implements Screen {

	@Override
	public ScreenName changePage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void runPage() {
		// TODO Auto-generated method stub

	}

	@Override
	public void printString() {
		System.out.println("Summary");
		System.out.println("Date : 2019-02-30 10:00 AM");
		System.out.println("Withdraw : $50");
		System.out.println("Balance : $10");
		System.out.println("");
		System.out.println("1. Transaction");
		System.out.println("2. Exit");
		System.out.println("Choose option[2]:");
	}

	@Override
	public void inputString(String page) {
		// TODO Auto-generated method stub

	}

}
