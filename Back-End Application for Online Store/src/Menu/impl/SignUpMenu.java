package Menu.impl;

import java.util.Scanner;

import Services.UserManagementService;
import enteties.User;
import enteties.impl.DefaultUser;
import Menu.Menu;
import Services.impl.DefaultUserManagementService;
import configs.ApplicationContext;

public class SignUpMenu implements Menu {
	private UserManagementService userManagementService;
	private ApplicationContext context;

	{
		userManagementService = DefaultUserManagementService.getInstance();
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		printMenuHeader();

		Scanner sc = new Scanner(System.in);
		System.out.println("Please Enter your first name: ");
		String firstName = sc.next();
		System.out.println("Please Enter your last name: ");
		String lastName = sc.next();
		System.out.println("Please Enter your email: ");
		String email = sc.next();
		System.out.println("Please Enter your password: ");
		String password = sc.next();

		userManagementService.getUsers();
		User user = new DefaultUser(firstName, lastName, email, password);

		String ERROR_PRINT = new String();
		ERROR_PRINT = UserManagementService.regirsterUser(user);
		if (ERROR_PRINT == null || ERROR_PRINT.isEmpty()) {
			context.setLoggedInUser(user);
			System.out.println("New user is created");

		}
		context.getMainMenu().start();
	}

	@Override
	public void printMenuHeader() {
		System.out.println("*******SIGN UP*******");

	}

}
