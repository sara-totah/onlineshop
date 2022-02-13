package Menu.impl;

import java.util.Scanner;

import Services.OrderManagementService;
import configs.ApplicationContext;
import enteties.Order;
import Menu.Menu;

public class MyOrdersMenu implements Menu {
	private ApplicationContext context;


	@Override
	public void start() {
		printMenuHeader();
		Menu menuToNavigate = null;

			if (context.getLoggedInUser() == null) {
				menuToNavigate = new MainMenu();
				System.out.println("Please, log in or create new account to see list of your orders");
				
				new MainMenu().start();
				return;
			}else {
				printOrdersToConsole();
		}
			new MainMenu().start();
			
		
	}

	private void printOrdersToConsole() {
		Order[] loggedInUserOrders = null;
		try {
			loggedInUserOrders = OrderManagementService.getOrdersByUserId(context.getLoggedInUser().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	if(loggedInUserOrders == null || loggedInUserOrders.length == 0) {
		System.out.println("Unfortunately, you don't have any orders yet. "
				+ "Navigate back to main menu to place a new order");
	}else {
		for (Order order : loggedInUserOrders) {
			System.out.println(order);
		}
		}
	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** MY ORDERS *****");
		
	}

}
