package shop;

import java.util.List;

public class User {

	public enum Role {

		ADMIN("admin"), CUSTOMER("customer");

		private Role(String role) {
			this.role = role;
		}

		public String role;

		public String getRole() {
			return role;
		}

	}

	private long id;
	private String email;
	private String password;
	private String name;
	private List<Order> orders;
	private Role role;

	public User() {

	}

	public User(long id, String email, String password, String name, List<Order> orders, Role role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.orders = orders;
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
