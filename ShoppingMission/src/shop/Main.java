package shop;

import java.util.HashMap;



public class Main {

	public static void main(String[] args) {

		ShoppingMall shoppingMall = new ShoppingMall("우리의 쇼핑몰!");

		
		/**
		 * 로그인이나 회원가입 실패 시 해당 화면에서 반복하지 않고 
		 * 홈 화면으로 이동해 다시 프로세스를 진행합니다.
		 * (바꿔도 됨!)
		 */
		while (true) {
			System.out.println("=============Home==============");
			System.out.print("1. 로그인 2. 회원가입  >> ");
			int homeMenu = Integer.parseInt(DataInput.sc.nextLine());
			switch (homeMenu) {
			case 1:
				User loginUser = shoppingMall.login(inputLoginData());
				if(loginUser == null) { // 실패 시 
					System.out.println("올바르지 않은 아이디 또는 비밀번호를 입력하셨습니다.");
					System.out.println("로그인 실패!");
					continue;

				}
				else { // 로그인 성공
					System.out.println(loginUser.getNickname() + "님 환영합니다!");
					// 역할별 분기 처리
				}
				break;
			case 2:
				if(shoppingMall.join(inputJoinData())) {
					
				}
				else {
					System.out.println("회원가입 실패!");
					continue;
				}
				break;
			}

		}

	}

	private static HashMap<String,String> inputJoinData() {
		
		String email = "";
		String password = "";
		String nickname = "";

		
		boolean isJoinFail = false;
		do {
			try {
				System.out.println("=================회원가입 화면=================");
				System.out.print("email :  ");
				email = DataInput.sc.nextLine();
				System.out.print("password :  ");
				password = DataInput.sc.nextLine();
				System.out.print("nickname : ");
				nickname = DataInput.sc.nextLine();
				
				
				// 검증로직 작성
				if(email.equals("aa"))
					throw new Exception();
				
				isJoinFail = false; // 입력 실패 후 재입력 시 값 초기화 방지
			} catch (Exception exception) {
				System.out.println("입력 값이 올바르지 않습니다.");
				isJoinFail = true;
			}
		} while (isJoinFail);

		HashMap<String, String> joinData = new HashMap<String, String>();
		joinData.put("email", email);
		joinData.put("password", password);
		joinData.put("nickname", nickname);
		
		return joinData;

	}

	/**
	 * @Description 자료구조 사용 겸 사용했습니다.
	 * 실패 시 계속 입력 로그인
	 * 성공 시 홈 메뉴로 이동
	 * 
	 */
	private static HashMap<String, String> inputLoginData() {
		String email = "";
		String password = "";

		boolean isLoginFail = false;
		do {
			try {
				System.out.println("=================로그인 화면=================");
				System.out.print("email :  ");
				email = DataInput.sc.nextLine();
				System.out.print("password :  ");
				password = DataInput.sc.nextLine();
				
				// 검증로직 작성
				if(email.equals("aa"))
					throw new Exception();
				
				isLoginFail = false;
			} catch (Exception exception) {
				System.out.println("입력 값이 올바르지 않습니다.");
				isLoginFail = true;
			}
		} while (isLoginFail);

		HashMap<String, String> loginData = new HashMap<String, String>();
		loginData.put("email", email);
		loginData.put("password", password);

		return loginData;
	}

}
