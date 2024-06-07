package shop;

public class Item {

	private long id;
	private Category category;
	private String content;
	private int price;
	
	public Item() {
		
	}

	public Item(long id, Category category, String content, int price) {
		this.id = id;
		this.category = category;
		this.content = content;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
