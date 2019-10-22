package javabootcamp;

import java.util.Scanner;

public class Main {


	public static void main(String[] args) {
		
		System.out.println("Main scanner is running.....");
		Scanner sc = new Scanner(System.in);
		String value = "";
		ScreenName page = ScreenName.LOGIN;
		Screen screen;
		ScreenFactory sf = new ScreenFactory();

		screen = sf.getScreen(page);
		screen.printString();
		
		while(sc.hasNext()) {
			value = sc.next();
			
			screen.inputString(value);
			
			ScreenName toPage = screen.changePage();
			
			if(toPage != null){
				screen = sf.getScreen(toPage);
			}
			
			screen.printString();
			
		}

	}

}
