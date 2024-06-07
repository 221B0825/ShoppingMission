package shop;

import java.util.List;

public class Order {

	private long id;
	private User customer;
	private List<Item> items;
	private int totalPrice;

	public Order() {

	}

	public Order(long id, User customer, List<Item> items, int totalPrice) {
		super();
		this.id = id;
		this.customer = customer;
		this.items = items;
		this.totalPrice = totalPrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
