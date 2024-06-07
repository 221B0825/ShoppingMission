package shop;

public class Admin extends User {

	private String department;

	public Admin() {
		super();
	}

	public Admin(String email, String password, String name, String department) {
		super(email, password, name);
		this.department = department;
	}

}
