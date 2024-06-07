package shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingMall {
	private String name;
	private List<User> users;
	private List<Item> items;
	
	public ShoppingMall() {};
	
	public ShoppingMall(String name) {
		this.name = name;
		this.users = new ArrayList<>();
		this.items = new ArrayList<>();
	}
	
	/**
	 * 1.로그인 2.회원가입
	 */
	public void showHome() {
		System.out.println("Home");
		System.out.print("1. 로그인 2. 회원가입  >> ");
	}
	public void showLogin() {
		System.out.println("로그인 화면");
		System.out.print("email :  ");
		String email = DataInput.sc.nextLine();
		System.out.print("password :  ");
		String password = DataInput.sc.nextLine();
		
		// 로그인 로직
		// 예외 처리
	}
	public void showJoin() {
		System.out.println("회원가입 화면");
		System.out.print("email :  ");
		String email = DataInput.sc.nextLine();
		System.out.print("password :  ");
		String password = DataInput.sc.nextLine();
		System.out.print("nickname :  ");
		String nickname = DataInput.sc.nextLine();

		
	}
	public void showMain() {
		
	}
	public List<Item> showItemList() {
		return null;
	}
	public List<Item> showItemListByCategory(){
		return null;
	}
}
