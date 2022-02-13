package Menu.impl;

import java.util.Scanner;

import Services.UserManagementService;
import Services.impl.DefaultUserManagementService;
import configs.ApplicationContext;
import enteties.User;
import Menu.Menu;

public class SignInMenu implements Menu {
	private UserManagementService userManagementService;
	private ApplicationContext context;
	
	{
		context = ApplicationContext.getInstance();
		userManagementService = DefaultUserManagementService.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();
		Scanner sc = new Scanner(System.in);

		System.out.println("Please, enter your email: ");
		String userEmail = sc.next();

		System.out.println("Please, enter your password: ");
		String userPassword = sc.next();

		
		User user = userManagementService.getUserByEmail(userEmail);
		
		if (user != null && user.getPassword().equals(user.getPassword())) {
			System.out.println("Glad to see you back %s %s" + user.getFirstName() + user.getLastName());
			context.setLoggedInUser(user);
		}else {
		System.out.println("Unfortunately, such login and password doesn't exist");
		}
		context.getMainMenu().start();

	}

	@Override
	public void printMenuHeader() {
		System.out.println("******** Sign In ********");
	}

}
