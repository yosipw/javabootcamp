package bootcamp.runner;

import java.util.Scanner;

import bootcamp.screen.EnumScreen;
import bootcamp.screen.FactoryScreen;
import bootcamp.screen.InterfaceScreenAction;

/**
 * @author Yosua_S
 *
 */
public class Runner {
	private static String value = "";
	private static InterfaceScreenAction screen;
	private static EnumScreen page = EnumScreen.LOGIN;
	private static FactoryScreen factoryScreen = new FactoryScreen();

	/**
	 *  Execute the scanner reader and perform navigation
	 */
	public static void run(){
		System.out.println("Main scanner is running.....");
		
		Scanner sc = new Scanner(System.in);
		screen = factoryScreen.setCurrentScreen(page);
		screen.printMessage();

		while(sc.hasNextLine()) {
			value = sc.nextLine();
			
			screen.readInput(value);
			
			EnumScreen toPage = screen.changePage();
			
			if(toPage != null){
				screen = factoryScreen.setCurrentScreen(toPage);
			}
			
			screen.printMessage();
		}
	}
}
