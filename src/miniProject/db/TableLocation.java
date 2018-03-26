package miniProject.db;

import java.io.Serializable;

public class TableLocation implements Serializable{
	private int tableNo;
	private int x;
	private int y;
//	public static int tableCount=0;
	
	public TableLocation() {
		// TODO Auto-generated constructor stub
	}

	public TableLocation(int tableNo, int x, int y) {
		super();
		this.tableNo = tableNo;
		this.x = x;
		this.y = y;
	}

	public int getTableNo() {
		return tableNo;
	}

	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(tableNo);
	}
	
	

}
