package miniProject.db;

import java.io.Serializable;

public class MenuOrder implements Serializable{
	private Menu menu;
	private int count;
	private int price;
	
	public MenuOrder() {
		// TODO Auto-generated constructor stub
	}
	
	public MenuOrder(Menu menu, int count, int price) {
		super();
		this.menu = menu;
		this.count = count;
		this.price = price;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return menu+"\t"+count+"\t"+price;
	}

}
