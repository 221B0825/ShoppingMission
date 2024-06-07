package shop;

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
