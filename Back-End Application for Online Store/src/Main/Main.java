package Main;
import Menu.Menu;
import Menu.impl.MainMenu;

public class Main {
	public static final String exit_command = "exit";
	
	public static void main(String[] args) {
		Menu mainMenu = new MainMenu();
		mainMenu.start();
	}
	
}

