package shop;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

	private List<Order> orders;

	public Customer() {
		super();
	}

	public Customer(String email, String password, String name) {
		super(email, password, name);
		this.orders = new ArrayList<Order>();
	}
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	
	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
