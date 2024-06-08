package shop;

public class Item {

	private String title;
	private String content;
	private Category category;
	private int price;
	
	public Item() {
		
	}

	public Item(String title, String content, int price, Category category) {
		super();
		this.title = title;
		this.content = content;
		this.category = category;
		this.price = price;
	}




	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "상품명 : " + title + " \n"
				+ "글 내용 : " + content + " \n"
				+ "가격 : " + price + " \n"
				+ "카테고리 : " + category.getName() + "\n";
	}
}
