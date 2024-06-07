package shop;

public enum Category {
	Food("food"), Living("living"), Beauty("beauty"), Sports("sports"), Pets("pets");

	private Category(String category) {
		this.category = category;
	}

	public String category;

	public String getCategory() {
		return category;
	}
}
