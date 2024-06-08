package shop;

import java.util.ArrayList;
import java.util.List;

public class Category {
	private String name;
	private List<Item> items;

	public Category() {
	};

	public Category(String name) {
		this.name = name;
		this.items = new ArrayList<Item>();
	}

	public String getName() {
		return name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void deleteItem(Item deleteItem) {
		items.remove(deleteItem);
	}

}
