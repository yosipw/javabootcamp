package bootcamp.screen;

/**
 * @author Yosua_S
 *
 */
public interface InterfaceScreenAction {
	
	
	/**
	 * Print messages and possible commands that will guide the user 
	 * to execute some action
	 */
	void printMessage();
	
	
	/**
	 * @return
	 * Return a specific page if the readInput fulfill a certain condition
	 */
	EnumScreen changePage();
	
	/**
	 * @param page
	 * Read the input value and perform action 
	 * whether compute it or redirect it to another page
	 */
	void readInput(String value);

}
