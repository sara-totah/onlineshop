package Menu.impl;

import java.util.Scanner;

import Services.ProductManagementService;
import Services.impl.DefaultProductManagementService;
import configs.ApplicationContext;
import enteties.Cart;
import enteties.Product;
import Menu.Menu;

public class ProductCatalogMenu implements Menu {
	private static final String CHECKOUT_COMMAND = "checkout";
	private ApplicationContext context;
	private ProductManagementService productManagementService;

	{
		context = ApplicationContext.getInstance();
		productManagementService = DefaultProductManagementService.getInstance();
	}

	@Override
	public void start() {
		Menu menuToNavigate = null;
		while (true) {
			printMenuHeader();
			printProductsToConsole();
			Scanner sc = new Scanner(System.in);

			System.out.print("Product ID to add to cart or enter 'checkout' to proceed with checkout: ");
			String userInput = sc.next();

			if (context.getLoggedInUser() == null) {
				menuToNavigate = new MainMenu();
				System.out.println("You are not logged in. Please, sign in or create new account");
				break;
			}

			if (userInput.equalsIgnoreCase(MainMenu.menu_command)) {
				menuToNavigate = new MainMenu();
				break;
			}

			if (userInput.equalsIgnoreCase(CHECKOUT_COMMAND)) {
				Cart sessionCart = context.getSessionCart();
				if (sessionCart == null || sessionCart.isEmpty()) {
					System.out.println(
							"Your cart is empty. Please, add product to cart first and then proceed with checkout");

				} else {
					menuToNavigate = new CheckoutMenu();
					break;
				}
			} else {
				Product productToAddToCart = fetchProduct(userInput);

				if (productToAddToCart == null) {
					System.out.println(
							"Please, enter product ID if you want to add product to cart. Or enter 'checkout' if you want to proceed with checkout. Or enter 'menu' if you want to navigate back to the main menu.");
					continue;
				}

				processAddToCart(productToAddToCart);

			}
		}

		menuToNavigate.start();

	}
	
	private void printProductsToConsole() {
		Product[] products = productManagementService.getProducts();
		for (Product product : products) {
			System.out.println(product);
		}
	}

	private Product fetchProduct(String userInput) {
		int productIdToAddToCart = Integer.parseInt(userInput);
		Product productToAddToCart = productManagementService.getProductById(productIdToAddToCart);

		return productToAddToCart;
	}

	private void processAddToCart(Product productToAddToCart) {
		context.getSessionCart().addProduct(productToAddToCart);
		System.out.printf("Product %s has been added to your cart. "
				+ "If you want to add a new product - enter the product id. "
				+ "If you want to proceed with checkout - enter word "
				+ "'checkout' to console %n", productToAddToCart.getProductName());

	}

	@Override
	public void printMenuHeader() {
		System.out.println("***** PRODUCT CATALOG *****");
		System.out.println(
				"Enter product id to add it to the cart or 'menu' if you want to navigate back to the main menu");
	}

}
