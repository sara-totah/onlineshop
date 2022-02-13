package Menu.impl;

import java.util.Scanner;

import configs.ApplicationContext;
import Menu.Menu;

public class ChangeEmailMenu implements Menu {
	
	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
	}
	
	@Override
	public void start() {
		printMenuHeader();
		System.out.print("Enter new email: ");
		Scanner sc = new Scanner(System.in);
		String userInput = sc.next();
		context.getLoggedInUser().setemail(userInput);
		System.out.println("Your email has been successfully changed");
		new MainMenu().start();
		
		
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** CHANGE EMAIL *****");
		
	}

}
