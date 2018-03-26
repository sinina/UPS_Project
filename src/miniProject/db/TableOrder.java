package miniProject.db;

import java.io.Serializable;
import java.util.ArrayList;

public class TableOrder implements Serializable{
	private int tableNo;
	private ArrayList<MenuOrder> orderlist;
	private int totalcost;
	private int remain;
	
	public TableOrder() {
		// TODO Auto-generated constructor stub
	}

	public TableOrder(int tableNo, ArrayList<MenuOrder> orderlist, int totalcost, int remain) {
		super();
		this.tableNo = tableNo;
		this.orderlist = orderlist;
		this.totalcost = totalcost;
		this.remain = remain;
	}



	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public ArrayList<MenuOrder> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(ArrayList<MenuOrder> orderlist) {
		this.orderlist = orderlist;
	}

	public int getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(int totalcost) {
		this.totalcost = totalcost;
	}
	
	public int getRemain() {
		return remain;
	}

	public void setRemain(int remain) {
		this.remain = remain;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return tableNo+","+orderlist+","+totalcost+","+remain;
		return String.valueOf(tableNo);
	}

}
