package Menu.impl;

import java.util.Scanner;

import configs.ApplicationContext;
import Menu.Menu;
import Main.Main;

public class SettingsMenu implements Menu {

	private static final String Settings = "1. Change Password" + System.lineSeparator() + "2. Change Email";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		Menu menuToNavigate = null;
		mainLoop: while (true) {
			printMenuHeader();
			
			if (context.getLoggedInUser() == null) {
				System.out.println("Please, log in or create new account to change your account settings");
				new MainMenu().start();
				return;
				
			} else {
				System.out.println(Settings);
				System.out.print("Please, enter option or type 'menu' to navigate back to the main menu: ");
				Scanner sc = new Scanner(System.in);
				String userInput = sc.next();
				if (userInput.equalsIgnoreCase(Main.exit_command)) {
					menuToNavigate = new MainMenu();
					break mainLoop;
				}
				
				int option = Integer.parseInt(userInput);
				switch (option) {
				case 1:
					menuToNavigate = new ChangePasswordMenu();
					break mainLoop;
				case 2:
					menuToNavigate = new ChangeEmailMenu();
					break mainLoop;
				default:
					System.out.println("Only 1, 2 is allowed. Try one more time");
					continue;
				}
			}
		}
		menuToNavigate.start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** SETTINGS *****");

	}

}
