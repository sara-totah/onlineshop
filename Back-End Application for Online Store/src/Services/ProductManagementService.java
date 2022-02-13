package Services;

import enteties.Product;

public interface ProductManagementService {
	Product[] getProducts();

	Product getProductById(int productIdToAddToCart);

}

