package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import fileIO.FileIO;
import miniProject.db.MenuOrder;
import miniProject.db.TableOrder;

public class BillPage extends JFrame {
   FileIO io = new FileIO();

   ArrayList<MenuOrder> menuOrders = new ArrayList<MenuOrder>();
   ArrayList<TableOrder> tableOrders = new ArrayList<TableOrder>();
   TableOrder tableOrder = new TableOrder();

   OrderPage o;

   public BillPage(int tableNo, OrderPage o) {
      this.o = o;
      setBounds(200, 0, 500, 700);
      setTitle("영수증");
      this.setLayout(null);

      tableOrders = (ArrayList<TableOrder>) io.readDB("TableOrder");
      for (int i = 0; i < tableOrders.size(); i++) {
         if (tableOrders.get(i).getTableNo() == tableNo) {
            tableOrder = tableOrders.get(i);
            menuOrders = tableOrder.getOrderlist();
         }
      }

      JLabel title = new JLabel("영수증");
      title.setFont(new Font("돋움", Font.BOLD, 30));
      add(title).setBounds(200, 0, 100, 100);

      JLabel tableName = new JLabel("테이블 번호 : " + String.valueOf(tableNo));
      add(tableName).setBounds(350, 30, 100, 100);
      JLabel form1 = null;
      JLabel form2 = null;
      JLabel form3 = null;
      int y=80;
      JLabel line1 =new JLabel("--------------------------------------------------------------------------------------------");
      JLabel line2 =new JLabel("--------------------------------------------------------------------------------------------");
      add(line1).setBounds(50, y-20, 400, 100);
      for (int i = 0; i < menuOrders.size(); i++) {
         form1 = new JLabel(menuOrders.get(i).getMenu().getMenuName());
         form2 = new JLabel(String.valueOf(menuOrders.get(i).getPrice()));
         form3 = new JLabel(String.valueOf(menuOrders.get(i).getCount()));
         
         add(form1).setBounds(100, y, 200, 100);
         add(form2).setBounds(240, y, 200, 100);
         add(form3).setBounds(340, y, 200, 100);
         y+=20;
      }
      add(line2).setBounds(50, y, 400, 100);
      

      

      JLabel totalcost = new JLabel("총 금액 : " + String.valueOf(tableOrder.getTotalcost()) + " 원");
      totalcost.setFont(new Font("돋움", Font.BOLD, 20));
      add(totalcost).setBounds(200, y+50, 200, 100);

      JButton ok = new JButton("영수증 확인");
      ok.setBackground(Color.WHITE);
      ok.setForeground(Color.DARK_GRAY);
      ok.setFont(new Font("돋움", Font.PLAIN, 14));
      ok.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();
            o.dispose();
         }
      });
      add(ok).setBounds(300, y+170, 180, 50);

      this.setVisible(true);
   }
}