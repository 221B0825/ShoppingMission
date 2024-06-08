package shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingMall {
	private String name;
	private List<User> users;
	private List<Item> items;
	private List<Category> categories;
	private List<Order> orders;

	// orderNum 처리 어떻게 할 지 ...
	private long orderNum = 0;

	public ShoppingMall() {
	};

	public ShoppingMall(String name) {
		this.name = name;
		this.users = new ArrayList<>();
		this.items = new ArrayList<>();
		this.categories = new ArrayList<>();
		this.orders = new ArrayList<>();

		// default data init
		users.add(new Admin("admin", "1234", "주인장", "고객관리팀"));
		users.add(new Customer("1234@gmail.com", "1234", "aa"));

		// ------------------
		categories.add(new Category("Food")); // 0
		categories.add(new Category("Living")); // 1
		categories.add(new Category("Beauty")); // 2
		categories.add(new Category("Sports")); // 3
		categories.add(new Category("Pets")); // 4
		// ------------------
		items.add(new Item("유기농흙당근", "신선 유기농으로 키운 산지직송 당근", 5000, categories.get(0)));
		categories.get(0).addItem(items.get(0));

		items.add(new Item("무지반팔티", "통기성 좋은 여름용 반팔티", 9900, categories.get(1)));
		categories.get(1).addItem(items.get(1));

		items.add(new Item("강력썬크림", "피부 건강을 위한 자외선 차단제", 12900, categories.get(2)));
		categories.get(2).addItem(items.get(2));

		items.add(new Item("배구공", "5호 크기 공인구", 24800, categories.get(3)));
		categories.get(3).addItem(items.get(3));

		items.add(new Item("튼튼건강사료", "충분한 영양 공급을 위한 사료", 40000, categories.get(4)));
		categories.get(4).addItem(items.get(4));
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
		users.add(new Customer(email, joinData.get("password"), nickname));
		return true;
	}

	private boolean isDuplicated(String email, String nickname) {
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				System.out.println("이미 존재하는 E-mail 입니다.");
				return true;
			} else if (user.getNickname().equals(nickname)) {
				System.out.println("이미 존재하는 닉네임입니다.");
				return true;
			}
		}
		return false;
	}

	/**
	 * 아 중복 제거하고싶다...살려줘
	 *
	 */
	private Category findCategoryByName(String categoryName) {
		for (Category category : categories) {
			if (category.getName().equals(categoryName))
				return category;
		}
		return null;
	}

	public void addCategory(String categoryName) {
		Category category = findCategoryByName(categoryName);
		if (category != null) {
			System.out.println("이미 존재하는 카테고리 입니다.");
		}

		categories.add(new Category(categoryName));
	}

	public void deleteCategory(String categoryName) {
		Category category = findCategoryByName(categoryName);
		if (category == null) {
			System.out.println("존재하지 않는 카테고리 입니다.");
			return;
		}
		categories.remove(category);
	}

	public void showItemList() {
		System.out.println("상품 목록");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(i + "번 째 상품");
			System.out.println(items.get(i).toString());
		}
	}

	public void showItemListByCategory(String categoryName) {
		Category category = findCategoryByName(categoryName);
		if (category == null) {
			System.out.println("존재하지 않는 카테고리 입니다.");
			return;
		}

		List<Item> itemsByCategory = category.getItems();
		System.out.println("상품 목록");
		for (int i = 0; i < itemsByCategory.size(); i++) {
			System.out.println(i + "번 째 상품");
			System.out.println(itemsByCategory.get(i).toString());
		}
	}

	public Item findItemById(int itemId) {
		if (0 > itemId || itemId >= items.size()) {// 범위 오류
			return null;
		}
		return items.get(itemId);
	}

	public void addItem(HashMap<String, String> addItemData) {
		Category category = findCategoryByName(addItemData.get("category"));
		if (category == null) {
			System.out.println("존재하지 않는 카테고리 입니다.");
			return;
		}
		Item item = new Item(addItemData.get("title"), addItemData.get("content"),
				Integer.parseInt(addItemData.get("price")), category);

		items.add(item);
		category.addItem(item);
	}

	public void updateItem(Item item, HashMap<String, String> updateItemData) {
		Category category = findCategoryByName(updateItemData.get("category"));
		if (category == null) {
			System.out.println("존재하지 않는 카테고리 입니다.");
			return;
		}

		item.setTitle(updateItemData.get("title"));
		item.setContent(updateItemData.get("content"));
		item.setPrice(Integer.parseInt(updateItemData.get("price")));
		item.setCategory(category);

	}

	public void deleteItem(Item deleteItem) {
		Category category = deleteItem.getCategory();
		category.deleteItem(deleteItem);

		items.remove(deleteItem);
	}

	/*
	 * ----------------------------------------------------------------------- 여기서부터
	 * 유저 서비스
	 */

	// itemTitle과 같은 게 있다면 item 반환 / 없다면 null 반환
	public Item isExistItem(String itemTitle) {
		for (Item item : items) {
			if (item.getTitle().equals(itemTitle)) {
				return item;
			}
		}
		return null;
	}

	public void addCustomerOrder(List<Item> orderItems, Customer customer) {
		if (orderItems.size() == 0) {
			System.out.println("주문하려는 상품이 없습니다.");
		} else {
			int totalPrice = 0;
			for (Item item : orderItems) {
				totalPrice += item.getPrice();
			}
			Order order = new Order(orderNum++, customer, orderItems, totalPrice);
			this.orders.add(order);
			System.out.println("주문이 완료되었습니다.");
		}

	}
}
