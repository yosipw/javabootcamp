package runner;

import java.util.Scanner;

import screen.InterfaceScreen;
import screen.FactoryScreen;
import screen.EnumScreen;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Main scanner is running.....");
		Scanner sc = new Scanner(System.in);
		String value = "";
		EnumScreen page = EnumScreen.LOGIN;
		InterfaceScreen screen;
		FactoryScreen fs= new FactoryScreen();
		screen = fs.getScreen(page);
		screen.printMessage();
		
		while(sc.hasNextLine()) {
			value = sc.nextLine();
			screen.readInput(value);
			
			EnumScreen toPage = screen.changePage();
			
			if(toPage != null){
				screen = fs.getScreen(toPage);
			}
			screen.printMessage();
		}

	}

}
