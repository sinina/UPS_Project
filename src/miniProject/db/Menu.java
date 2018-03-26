package miniProject.db;

import java.io.Serializable;

public class Menu implements Serializable{
	private String menuName;
	private int price;
	private String category;
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public Menu(String menuName, int price, String category) {
		super();
		this.menuName = menuName;
		this.price = price;
		this.category = category;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return menuName+","+price+","+category;
	}

}
