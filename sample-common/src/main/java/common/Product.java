package common;

public class Product {

	private Integer id;
	private String name;
	private int price;

	public Product() {

	}

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public Product(String name) {
		super();
		this.name = name;
	}

	public Product(Integer id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
