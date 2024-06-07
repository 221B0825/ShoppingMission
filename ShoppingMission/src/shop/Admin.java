package shop;

public class Admin extends User {

	private String department;

	public Admin() {
		super();
	}

	public Admin(long id, String email, String password, String name, Role role, String department) {
		super(id, email, password, name, role);
		this.department = department;
	}

}
