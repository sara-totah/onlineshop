package Menu.impl;

import java.util.Scanner;

import configs.ApplicationContext;
import Menu.Menu;
import Main.Main;

public class MainMenu implements Menu {
	public static final String menu_command = "menu";
	private static final String main_menu_for_logged_out_user = "Please Enter number to proceed:" + System.lineSeparator() +"1.Sign Up"
			+ System.lineSeparator() + "2.Sign In" + System.lineSeparator() + "3.Product Catalog"
			+ System.lineSeparator() + "4.My Order" + System.lineSeparator() + "5.Settings" + System.lineSeparator()
			+ "6.Customer List";

	private static final String main_menu_for_logged_in_user = "Please Enter number to proceed: 1.Sign Up"
			+ System.lineSeparator() + "2.Sign Out" + System.lineSeparator() + "3.Product Catalog"
			+ System.lineSeparator() + "4.My Order" + System.lineSeparator() + "5.Settings" + System.lineSeparator()
			+ "6.Customer List";

	private ApplicationContext context;

	{
		context = ApplicationContext.getInstance();
	}

	@Override
	public void start() {
		if (context.getMainMenu() == null) {
			context.setMainMenu(this);
		}

		Menu menuToNavigate = null;
		mainLoop: while (true) {
			printMenuHeader();

			Scanner sc = new Scanner(System.in);

			System.out.print("User input: ");
			String userInput = sc.next();

			if (userInput.equalsIgnoreCase(Main.exit_command)) {
				System.exit(0);
			} else {
				int commandNumber = Integer.parseInt(userInput);
				switch (commandNumber) {

				case 1:
					menuToNavigate = new SignUpMenu();
					break;
				case 2:
					if (context.getLoggedInUser() == null) {
						menuToNavigate = new SignInMenu();
					} else {
						menuToNavigate = new SignOutMenu();
					}
					break;
				case 3:
					menuToNavigate = new ProductCatalogMenu();
					break;
				case 4:
					menuToNavigate = new MyOrdersMenu();
					break;
				case 5:
					menuToNavigate = new SettingsMenu();
					break;
				case 6:
					menuToNavigate = new CustomerListMenu();
					break;
				default:
					System.out.println("Only 1, 2, 3, 4, 5, 6 is allowed. Try one more time");
					continue;
				}
			}		menuToNavigate.start();

		}

	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MAIN MENU *****");
		if (context.getLoggedInUser() == null) {
			System.out.println(main_menu_for_logged_out_user);
		} else {
			System.out.println(main_menu_for_logged_in_user);
		}
	}

}
