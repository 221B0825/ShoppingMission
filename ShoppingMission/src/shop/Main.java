package shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			System.out.println("1.주문 2.주문 변경 3.주문 취소 4.나의 주문 목록 5.상품 목록 보기 6.카테고리별 목록 7.로그아웃");
			System.out.print("메뉴 선택: ");
			int customerMenu = Integer.parseInt(DataInput.sc.nextLine());
			switch (customerMenu) {
			case 1:
				shoppingMall.addCustomerOrder(inputAddOrderItem(shoppingMall), customer);
				break;
			case 2:
				shoppingMall.updateCustomerOrder(inputUpdateOrderData(shoppingMall, customer), customer);
				break;
			case 3:
				shoppingMall.cancelCustomerOrder(inputCancelOrderData(shoppingMall, customer), customer);
				break;
			case 4:
				shoppingMall.showOrderList(customer);
				break;
			case 5:
				shoppingMall.showItemList();
				break;
			case 6:
				shoppingMall.showItemListByCategory(inputCategoryName());
				break;
			case 7:
				return;
			}
		}

	}

	private static long inputCancelOrderData(ShoppingMall shoppingMall, Customer customer) {
		System.out.println("=================주문 목록=================");
		shoppingMall.showOrderList(customer);
		System.out.print("삭제할 주문 번호: ");
		long orderId = DataInput.sc.nextLong() - 1;
		DataInput.sc.nextLine();
		return orderId;
	}

	private static Order inputUpdateOrderData(ShoppingMall shoppingMall, Customer customer) {
		System.out.println("=================주문 목록=================");
		shoppingMall.showOrderList(customer);
		System.out.print("변경할 주문 번호 : ");
		// 실제 id 값은 0부터 시작
		long orderId = DataInput.sc.nextLong() - 1;
		// sc 한번 읽어줘야 다음 입력값 제대로 들어옴
		DataInput.sc.nextLine();
		Order order = shoppingMall.getCustomerOrder(orderId, customer);
		if (order == null) {
			System.out.println("잘못된 주문번호 입니다.");
			return null;
		} else {
			System.out.println("=================주문 변경=================");
			System.out.println("1. 물품 삭제 2. 물품 추가 주문");
			String selected = DataInput.sc.nextLine();
			switch (selected) {
			case "1":
				// 물품 삭제된 오더 리스트를 오더 번호와 함께 반환
				order = inputDeleteOrderItem(shoppingMall, order);
				
				break;
			case "2":
				// 물품 주문과 동일한 로직 사용
				List<Item> addOrderItems = inputAddOrderItem(shoppingMall);
				// 새로 주문한 물품들 추가
				for(Item item : addOrderItems) {
					order.getItems().add(item);
				}
				break;

			default:
				break;
			}
			return order;
		}
	}

	private static Order inputDeleteOrderItem(ShoppingMall shoppingMall, Order order) {
		System.out.println("=================물품 삭제=================");
		for(Item item : order.getItems()) {
			System.out.println(item.toString());
		}
		while(true) {
			System.out.println("*물품 삭제 종료를 원하시면 X를 입력해 주십시오.");
			System.out.print("삭제하려는 물품 이름: ");
			String itemTitle = DataInput.sc.nextLine();
			
			if(itemTitle.equalsIgnoreCase("x")) {
				break;
			}
			
			Item item = shoppingMall.isExistItem(itemTitle);
			if(item == null) {
				System.out.println("존재하지 않는 물품입니다.");
				continue;
			}

			if(order.getItems().contains(item)) {
				order.getItems().remove(item);
				System.out.println(item.getTitle()+" 물품 삭제 완료");
			}else {
				System.out.println("주문 내역에 존재하지 않는 물품입니다.");
			}
		}
		
		return order;
	}

	private static List<Item> inputAddOrderItem(ShoppingMall shoppingMall) {
		List<Item> orderItems = new ArrayList<Item>();
		System.out.println("=================물품 주문=================");
		while (true) {
			System.out.println("*주문 종료를 원하시면 X를 입력해 주십시오.");
			System.out.print("주문하려는 물품 이름: ");
			String itemTitle = DataInput.sc.nextLine();
			if (itemTitle.equalsIgnoreCase("x")) {
				break;
			}

			Item item = shoppingMall.isExistItem(itemTitle);

			if (item == null) {
				System.out.println("존재하지 않는 물품입니다.");
				continue;
			} else {
				orderItems.add(item);
			}
		}

		// orderItems에 아무것도 안담겨있으면 addCustomerOrder 내부에서 처리
		return orderItems;
	}

	private static void handleAdminMenu(Admin admin, ShoppingMall shoppingMall) {
		while (true) {
			System.out.println("=================관리자 메뉴=================");
			System.out.println("1.상품 등록 2.상품 변경 3.상품 삭제 4.카테고리 등록 5.카테고리 삭제 6.상품 목록 보기 7.카테고리 별 목록 8.전체 주문 목록 9.로그아웃");
			System.out.print("메뉴 선택: ");
			int customerMenu = Integer.parseInt(DataInput.sc.nextLine());
			switch (customerMenu) {
			case 1:
				shoppingMall.addItem(inputAddItemData());
				break;
			case 2:
				Item updateItem = shoppingMall.findItemById(inputUpdateItemId());
				if (updateItem == null) {
					System.out.println("존재하지 않는 데이터입니다.");
					break;
				}
				shoppingMall.updateItem(updateItem, inputUpdateItemData());
				break;
			case 3:
				Item deleteItem = shoppingMall.findItemById(inputDeleteItemId());
				if (deleteItem == null) {
					System.out.println("존재하지 않는 데이터입니다.");
					break;
				}
				shoppingMall.deleteItem(deleteItem);
				break;
			case 4:
				shoppingMall.addCategory(inputAddCategoryData());
				break;
			case 5:
				shoppingMall.deleteCategory(inputDeleteCategoryData());
				break;
			case 6:
				shoppingMall.showItemList();
				break;
			case 7:
				shoppingMall.showItemListByCategory(inputCategoryName());
				break;
			case 8:
				break;
			case 9:
				return;
			}
		}
	}

	private static String inputCategoryName() {
		System.out.println("=================카테고리 별 상품 목록 화면=================");
		System.out.print("카테고리 명을 입력해주세요 : ");
		return DataInput.sc.nextLine();
	}

	private static int inputDeleteItemId() {
		System.out.println("=================상품 삭제 화면=================");
		System.out.print("삭제할 데이터의 ID를 입력해주세요 : ");
		return Integer.parseInt(DataInput.sc.nextLine());
	}

	private static int inputUpdateItemId() {
		System.out.println("=================상품 수정 화면=================");
		System.out.print("수정할 데이터의 ID를 입력해주세요 : ");
		return Integer.parseInt(DataInput.sc.nextLine());
	}

	private static String inputDeleteCategoryData() {
		System.out.println("=================카테고리 삭제 화면=================");
		System.out.print("삭제할 카테고리 명을 입력하세요 : ");
		return DataInput.sc.nextLine();
	}

	private static String inputAddCategoryData() {
		System.out.println("=================카테고리 추가 화면=================");
		System.out.print("추가할 카테고리 명을 입력하세요 : ");
		return DataInput.sc.nextLine();
	}

	private static HashMap<String, String> inputAddItemData() {
		HashMap<String, String> addItemData = new HashMap<>();
		System.out.println("=================상품 추가 화면=================");
		System.out.print("title : ");
		addItemData.put("title", DataInput.sc.nextLine());
		System.out.print("content : ");
		addItemData.put("content", DataInput.sc.nextLine());
		System.out.print("price : ");
		addItemData.put("price", DataInput.sc.nextLine());
		System.out.print("category : ");
		addItemData.put("category", DataInput.sc.nextLine());

		return addItemData;
	}

	private static HashMap<String, String> inputUpdateItemData() {
		HashMap<String, String> updateItemData = new HashMap<>();
		System.out.print("title : ");
		updateItemData.put("title", DataInput.sc.nextLine());
		System.out.print("content : ");
		updateItemData.put("content", DataInput.sc.nextLine());
		System.out.print("price : ");
		updateItemData.put("price", DataInput.sc.nextLine());
		System.out.print("category : ");
		updateItemData.put("category", DataInput.sc.nextLine());

		return updateItemData;
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

				// 이메일 형식 검증
				if (!email.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z]+)*$"))
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

				// 이메일 형식 검증
				if (!email.equals("admin") && !email.matches("^[_0-9a-zA-Z-]+@[0-9a-zA-Z-]+(.[_0-9a-zA-Z]+)*$"))
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
