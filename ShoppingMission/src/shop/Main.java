package shop;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {

		ShoppingMall shoppingMall = new ShoppingMall("우리의 쇼핑몰!");

		/**
		 * 로그인이나 회원가입 실패 시 해당 화면에서 반복하지 않고 홈 화면으로 이동해 다시 프로세스를 진행합니다. (바꿔도 됨!)
		 */
		while (true) {
			System.out.println("=============Home==============");
			System.out.print("1. 로그인 2. 회원가입  >> ");
			int homeMenu = Integer.parseInt(DataInput.sc.nextLine());
			switch (homeMenu) {
			case 1:
				User loginUser = shoppingMall.login(inputLoginData());
				if (loginUser == null) { // 실패 시
					System.out.println("올바르지 않은 아이디 또는 비밀번호를 입력하셨습니다.");
					System.out.println("로그인 실패!");
					continue;

				} else { // 로그인 성공
					System.out.println(loginUser.getNickname() + "님 환영합니다!");

					if (loginUser instanceof Admin) { // 관리자 메뉴
						Admin admin = (Admin) loginUser;
						handleAdminMenu(admin, shoppingMall);
					} else if (loginUser instanceof Customer) // 고객 메뉴
					{
						Customer customer = (Customer) loginUser;
						handleCustomerMenu(customer, shoppingMall);
					}
				}
				break;
			case 2:
				if (shoppingMall.join(inputJoinData())) {

				} else {
					System.out.println("회원가입 실패!");
					continue;
				}
				break;
			}

		}

	}

	private static void handleCustomerMenu(Customer customer, ShoppingMall shoppingMall) {
		while (true) {
			System.out.println("=================고객 메뉴=================");
			System.out.print("1.주문 2.주문 변경 3.주문 취소 4.나의 주문 목록 5.상품 목록 보기 6.카테고리별 목록 7.로그아웃");
			int customerMenu = Integer.parseInt(DataInput.sc.nextLine());
			switch (customerMenu) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				return;
			}
		}

	}

	private static void handleAdminMenu(Admin admin, ShoppingMall shoppingMall) {
		while (true) {
			System.out.println("=================관리자 메뉴=================");
			System.out.println("1.상품 등록 2.상품 변경 3.상품 삭제 4.카테고리 등록 5.카테고리 삭제 6.상품 목록 보기 7.카테고리 별 목록 8.전체 주문 목록 9.로그아웃");
			int customerMenu = Integer.parseInt(DataInput.sc.nextLine());
			switch (customerMenu) {
			case 1:
				inputItemData();
				shoppingMall.addItem();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				return;
			}
		}
	}

	private static void inputItemData() {
		System.out.println("=================상품 추가 화면=================");
		System.out.print("title : ");
		String title = DataInput.sc.nextLine();
		System.out.print("content :  ");
		String content  = DataInput.sc.nextLine();
		System.out.print("price : ");
		int price = Integer.parseInt(DataInput.sc.nextLine());
		
		new Item(title, content, price, Category.Food);

	}

	private static HashMap<String, String> inputJoinData() {

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
				if (email.equals("aa"))
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
	 * @Description 자료구조 사용 겸 사용했습니다. 실패 시 계속 입력 로그인 성공 시 홈 메뉴로 이동
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
				if (email.equals("aa"))
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
