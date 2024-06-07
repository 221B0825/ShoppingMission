package shop;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class ShoppingMall {
	private String name;
	private List<User> users;
	private List<Item> items;

	public ShoppingMall() {
	};

	public ShoppingMall(String name) {
		this.name = name;
		this.users = new ArrayList<>();
		this.items = new ArrayList<>();
	}

	public User login(HashMap<String, String> loginData) {
		String email = loginData.get("email");
		String password = loginData.get("password");

		for (User user : users) {
			if (user.getEmail().equals(email)) // 아이디 매칭 성공
			{
				if (user.getPassword().equals(password)) // 로그인 성공
				{
					return user;
				}
				return null; // 비밀번호 오류
			}
		}
		return null; // 매칭되는 아이디 존재 X
	}

	/**
	 * @Description 우선 이메일과 닉네임에 대해 중복체킹을 진행했고 둘 중 하나라도 틀렸을 시 회원가입 실패에 대한 공통처리했습니다.
	 *              (분리해도됨!)
	 * 
	 */
	public boolean join(HashMap<String, String> joinData) {
		// 중복 검증
		String email = joinData.get("email");
		String nickname = joinData.get("nickname");

		if (isDuplicated(email, nickname))
			return false;

		
		// 중복에 걸리지 않았다면 회원가입 성공
		users.add(new User(email, joinData.get("password"), nickname));
		return true;
	}

	private boolean isDuplicated(String email, String nickname) {
		for (User user : users) {
			if (user.getEmail().equals(email))
			{
				System.out.println("이미 존재하는 E-mail 입니다.");
				return true;
			}
			else if(user.getNickname().equals(nickname)){
				System.out.println("이미 존재하는 닉네임입니다.");
				return true;
			}
		}
		return false;
	}

	public List<Item> showItemList() {
		return null;
	}

	public List<Item> showItemListByCategory() {
		return null;
	}

}
