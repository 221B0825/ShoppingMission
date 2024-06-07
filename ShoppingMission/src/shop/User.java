package shop;

public abstract class User {

	/**
	 * Enum제거
	 */
	
	// id처리가 애매해서 일단 빼놓겠습니다. 혹시 추가하실거면 하셔도 됩니다!
	private String email;
	private String password;
	private String nickname;
	
	public User() {

	}

	public User( String email, String password, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.nickname = nickname;
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


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


}
