package shop;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

	private List<Order> orders;

	public Customer() {
		super();
	}

	public Customer(long id, String email, String password, String name, Role role) {
		super(id, email, password, name, role);
		this.orders = new ArrayList<Order>();
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
