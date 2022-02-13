package Services;

import enteties.Order;

public interface OrderManagementService {

	
	void addOrder(Order order);
	static Order[] getOrdersByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	Order[] getOrders();

}
