package miniProject.db;

import java.io.Serializable;
import java.util.Date;

public class DaySales implements Serializable{

   private  String date; // 날짜
   private int totalSales;// 매출
   private int expenses;// 가게유지비
   private int soonSales;// 순매출
   private int cash;// 현금
   private boolean profit;// 흑자여부

   public DaySales() {
   }

   public DaySales(String date, int totalSales, int expenses, int cash) {
      this.date = date;
      this.totalSales = totalSales;
      this.expenses = expenses;
      this.soonSales= totalSales - expenses;
      this.profit= soonSales >= 0 ? true : false;
      this.cash = cash;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public int getTotalSales() {
      return totalSales;
   }

   public void setTotalSales(int totalSales) {
      this.totalSales = totalSales;
      this.soonSales= totalSales - expenses;
      this.profit= soonSales >= 0 ? true : false;
   }

   public int getExpenses() {
      return expenses;
   }

   public void setExpenses(int expenses) {
      this.expenses = expenses;
      this.soonSales= totalSales - expenses;
      this.profit= soonSales >= 0 ? true : false;
   }

   public int getSoonSales() {
      return soonSales;
   }

   public void setSoonSales(int soonSales) {
      this.soonSales = soonSales;
   }

   public int getCash() {
      return cash;
   }

   public void setCash(int cash) {
      this.cash = cash;
   }

   public boolean isProfit() {
      return profit;
   }

   public void setProfit(boolean profit) {
      this.profit = profit;
   }
   
   @Override
	public String toString() {
		// TODO Auto-generated method stub
		return date;
	}

}