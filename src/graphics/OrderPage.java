package graphics;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fileIO.FileIO;
import miniProject.db.DaySales;
import miniProject.db.Menu;
import miniProject.db.MenuOrder;
import miniProject.db.TableOrder;

public class OrderPage extends JFrame implements ActionListener {

	FileIO io = new FileIO();

	int tableNo;
	JTextField moneyToReceiveT;
	JTextField moneyReceivedT;
	int fieldSelect;
	String[] orderedMenu;
	JList orderedList;
	String selectList;
	int totalCost = 0;
	int remain = 0;// 남은 금액
	JTextField ChangeT;

	HashMap<String, Menu> menus = new HashMap<String, Menu>();
	ArrayList<MenuOrder> menuOrders = new ArrayList<MenuOrder>();
	ArrayList<TableOrder> tableOrders = new ArrayList<TableOrder>();
	TableOrder tableOrder = new TableOrder();
	ArrayList<DaySales> daySales = new ArrayList<DaySales>();
	DaySales daySale = new DaySales();
	SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
	ViewTables tableFrame;

	public OrderPage() {

	}

	public OrderPage(int tableNo, ViewTables tableFrame) {
		this(tableNo);
		this.tableFrame = tableFrame;
	}

	public OrderPage(int tableNo) {
		this.tableNo = tableNo;

		// 메뉴읽음
		ArrayList<Menu> ms = (ArrayList<Menu>) io.readDB("menu");
		for (int i = 0; i < ms.size(); i++) {
			menus.put(ms.get(i).getMenuName(), ms.get(i));
		}

		// 매출읽음
		daySales = (ArrayList<DaySales>) io.readDB("DaySales");
		for (int i = 0; i < daySales.size(); i++) {
			if (daySales.get(i).getDate().equals(ft.format(new Date()))) {
				daySale = daySales.get(i);
				// System.out.println(daySale);
				daySales.remove(i);
			}
		}

		// 테이블주문정보읽음
		tableOrders = (ArrayList<TableOrder>) io.readDB("TableOrder");
		for (int i = 0; i < tableOrders.size(); i++) {
			if (tableOrders.get(i).getTableNo() == tableNo) {
				tableOrder = tableOrders.get(i);
				menuOrders = tableOrder.getOrderlist();
			}
		}
		totalCost = tableOrder.getTotalcost();
		remain = tableOrder.getRemain();

		setBounds(0, 0, 1200, 900);
		setTitle("주문창");
		this.setLayout(null);

		Panel pNorth = new Panel();// pWest+pCenter+pEast
		Panel pWest = new Panel();// pOrdered+pManagement
		Panel pCenter = new Panel();// 메뉴선택
		Panel pEast = new Panel();// pBill+pNum
		Panel pSouth = new Panel();// 주문/결제버튼
		Panel pBill = new Panel();// 영수증
		Panel pNum = new Panel();// 번호판
		Panel pOrdered = new Panel();// 주문한 메뉴 목록
		Panel pManagement = new Panel();// 주문 추가/삭제

		pNorth.setBounds(20, 10, 1150, 700);
		this.add(pNorth);
		this.add(pSouth);
		pNorth.setLayout(new GridLayout(0, 3, 25, 0));
		pNorth.add(pWest);
		pNorth.add(pCenter);
		pNorth.add(pEast);
		pEast.setLayout(new GridLayout(2, 1));
		pEast.add(pBill);
		pEast.add(pNum);
		pWest.setLayout(null);
		pWest.add(pOrdered);
		pWest.add(pManagement);
		pCenter.setLayout(null);
		pOrdered.setLayout(null);

		pOrdered.setBounds(0, 80, 360, 530);

		// pWest-pOrdered
		// JList orderedList =new JList();
		orderedMenu = new String[menuOrders.size()];
		for (int i = 0; i < orderedMenu.length; i++) {
			orderedMenu[i] = new String("    " + menuOrders.get(i).getMenu().getMenuName() + "          "
					+ menuOrders.get(i).getCount() + "          " + menuOrders.get(i).getPrice());

		}
		orderedList = new JList(orderedMenu);
		orderedList.setBounds(0, 0, 360, 530);
		orderedList.setFont(new Font("돋음", Font.PLAIN, 20));
		orderedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		orderedList.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectList = (String) orderedList.getSelectedValue();
				String[] sarr = selectList.split("    ");
				selectList=sarr[1];
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		pOrdered.add(orderedList);

		// 테이블번호
		JLabel tableName = new JLabel("테이블 " + String.valueOf(tableNo));
		tableName.setFont(new Font("돋음", Font.BOLD, 30));
		tableName.setBounds(0, 0, 360, 80);
		pWest.add(tableName);

		// pEast-pBill
		pBill.setLayout(new GridLayout(8, 1));
		JLabel totalCostL = new JLabel("총금액");
		pBill.add(totalCostL);
		JTextField totalCostT = new JTextField(30);
		totalCostT.setText(String.valueOf(tableOrder.getTotalcost()));
		pBill.add(totalCostT);
		JLabel moneyToReceive = new JLabel("받을 금액");// 남은금액
		pBill.add(moneyToReceive);
		moneyToReceiveT = new JTextField(30);
		moneyToReceiveT.setText(String.valueOf(tableOrder.getRemain()));
		moneyToReceiveT.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				moneyToReceiveT.setText("");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		pBill.add(moneyToReceiveT);
		JLabel moneyReceived = new JLabel("받은 금액");
		pBill.add(moneyReceived);
		moneyReceivedT = new JTextField(30);
		pBill.add(moneyReceivedT);
		JLabel Change = new JLabel("거스름돈");
		pBill.add(Change);
		ChangeT = new JTextField(30);
		pBill.add(ChangeT);

		// pEast-pNum
		pNum.setLayout(new GridLayout(4, 3, 3, 3));
		JButton[] num = new JButton[12];
		for (int i = 0; i < 9; i++) {
			num[i] = new JButton(String.valueOf(i + 1));
		}
		num[9] = new JButton("00");
		num[10] = new JButton("0");
		num[11] = new JButton("<-");
		num[11].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (moneyToReceiveT.hasFocus()) {
					if (moneyToReceiveT.getText().equals("")) {
					} else {
						StringBuilder sb = new StringBuilder(moneyToReceiveT.getText());
						sb.delete(sb.length() - 1, sb.length());
						moneyToReceiveT.setText(sb.toString());
					}
				} else if (moneyReceivedT.hasFocus()) {
					if (moneyReceivedT.getText().equals("")) {
					} else {
						StringBuilder sb = new StringBuilder(moneyReceivedT.getText());
						sb.delete(sb.length() - 1, sb.length());
						moneyReceivedT.setText(sb.toString());
					}
				}
			}

		});

		for (int i = 0; i < num.length; i++) {

			num[i].setFont(new Font("돋음", Font.PLAIN, 15));
			num[i].setBackground(Color.GRAY);
			num[i].setForeground(Color.WHITE);
			num[i].setFocusable(false);
			pNum.add(num[i]);
		}

		for (int i = 0; i < num.length - 1; i++) {
			num[i].addActionListener(new NumberInsert());
		}

		// pSouth
		pSouth.setBounds(0, 700, 1200, 200);
		pSouth.setLayout(null);
		JButton orderB = new JButton("주문");
		orderB.setBackground(Color.WHITE);
		orderB.setForeground(Color.DARK_GRAY);
		orderB.setFont(new Font("돋움", Font.BOLD, 20));
		orderB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (menuOrders.size() == 0) {
					dispose();
				} else {
					if (totalCost != 0) {
						totalCost = 0;
					}

					for (int i = 0; i < menuOrders.size(); i++) {
						totalCost += menuOrders.get(i).getPrice();
					}
					remain = totalCost;
					ArrayList<TableOrder> a = io.readDB("TableOrder");
					io.removeObject("TableOrder", tableOrder);
					a = io.readDB("TableOrder");
					tableOrder = new TableOrder(tableNo, menuOrders, totalCost, remain);
					io.editDB("TableOrder", tableOrder);
					a = io.readDB("TableOrder");
					totalCostT.setText(String.valueOf(totalCost));
					moneyToReceiveT.setText(String.valueOf(totalCost));

					tableFrame.orderSuccess(tableNo);

					JOptionPane.showMessageDialog(pCenter, "주문되였습니다.");
					if (JOptionPane.OK_OPTION == 0) {
						dispose();
					}
				}
			}

		});
		orderB.setBounds(20, 20, 750, 130);
		pSouth.add(orderB);
		JButton cardB = new JButton("카드");
		cardB.addActionListener(this);
		cardB.setBounds(803, 20, 170, 130);
		cardB.setBackground(Color.WHITE);
		cardB.setForeground(Color.DARK_GRAY);
		pSouth.add(cardB);
		JButton cashB = new JButton("현금");
		cashB.addActionListener(this);
		cashB.setBounds(999, 20, 170, 130);
		cashB.setBackground(Color.WHITE);
		cashB.setForeground(Color.DARK_GRAY);
		pSouth.add(cashB);
		//

		// pCenter
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setSize(360, 700);
		HashSet<String> category = new HashSet<String>();
		Set<Entry<String, Menu>> entry = menus.entrySet();
		Iterator<Entry<String, Menu>> entryIter = entry.iterator();
		Entry<String, Menu> temp = null;

		while (entryIter.hasNext()) {
			temp = entryIter.next();
			category.add(temp.getValue().getCategory());
		}
		Iterator<String> categoryIter = category.iterator();
		HashMap<String, Panel> pCategory = new HashMap<String, Panel>();
		while (categoryIter.hasNext()) {
			String str = categoryIter.next();
			pCategory.put(str, new Panel());
			pCategory.get(str).setLayout(new FlowLayout());
			tabbedPane.setFont(new Font("돋음", Font.PLAIN, 15));
			tabbedPane.setBackground(Color.WHITE);
			tabbedPane.setForeground(Color.DARK_GRAY);
			tabbedPane.add(str, pCategory.get(str));
		}

		JButton[] menuB = new JButton[menus.size()];
		entryIter = entry.iterator();
		for (int i = 0; i < menus.size(); i++) {
			temp = entryIter.next();
			menuB[i] = new JButton(
					"<html> " + temp.getValue().getMenuName() + " <br>" + temp.getValue().getPrice() + "</html>");
			menuB[i].setFont(new Font("돋음", Font.PLAIN, 18));
			menuB[i].setForeground(Color.DARK_GRAY);
			menuB[i].setBackground(Color.WHITE);
			menuB[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					// menuOrders에 추가
					String str = e.getActionCommand();
					String[] sarr = str.split(" ");
					Set<Entry<String, Menu>> entry = menus.entrySet();
					Iterator<Entry<String, Menu>> entryIter = entry.iterator();
					Entry<String, Menu> temp = null;
					int re = 0;
					while (entryIter.hasNext()) {
						temp = entryIter.next();
						if (temp.getValue().getMenuName().equals(sarr[1])) {

							for (int i = 0; i < menuOrders.size(); i++) {
								if (menuOrders.get(i).getMenu().getMenuName().equals(sarr[1])) {// menuOrders에있으면
									menuOrders.get(i).setCount(menuOrders.get(i).getCount() + 1);
									menuOrders.get(i).setPrice(
											menuOrders.get(i).getCount() * menuOrders.get(i).getMenu().getPrice());
									re = -1;
								}
							}
							if (re == 0) {
								menuOrders.add(new MenuOrder(temp.getValue(), 1, temp.getValue().getPrice()));
							}
						}
					}

					// 리스트에 추가
					orderedMenu = new String[menuOrders.size()];
					for (int i = 0; i < orderedMenu.length; i++) {

						orderedMenu[i] = new String("    " + menuOrders.get(i).getMenu().getMenuName() + "        "
								+ menuOrders.get(i).getCount() + "        " + menuOrders.get(i).getPrice());

					}
					orderedList.setListData(orderedMenu);

				}

			});
			pCategory.get(temp.getValue().getCategory()).add(menuB[i]);
		}

		pCenter.add(tabbedPane);

		// pWest-pManagement
		pManagement.setBounds(0, 615, 360, 70);
		pManagement.setLayout(new GridLayout(1, 4));
		JButton[] menuManagement = new JButton[4];
		menuManagement[0] = new JButton("선택삭제");
		menuManagement[0].setBackground(Color.WHITE);
		menuManagement[0].setForeground(Color.DARK_GRAY);
		menuManagement[0].setFont(new Font("돋움",Font.PLAIN,12));
		menuManagement[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < menuOrders.size(); j++) {
					if (menuOrders.get(j).getMenu().getMenuName().equals(selectList)) {
						menuOrders.remove(j);
					}
				}
				orderedMenu = new String[menuOrders.size()];
				for (int i = 0; i < orderedMenu.length; i++) {
					orderedMenu[i] = new String("    " + menuOrders.get(i).getMenu().getMenuName() + "          "
							+ menuOrders.get(i).getCount() + "          " + menuOrders.get(i).getPrice());

				}
				orderedList.setListData(orderedMenu);
			}

		});
		menuManagement[1] = new JButton("전체삭제");
		menuManagement[1].setBackground(Color.WHITE);
		menuManagement[1].setForeground(Color.DARK_GRAY);
		menuManagement[1].setFont(new Font("돋움",Font.PLAIN,12));
		menuManagement[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuOrders = new ArrayList<MenuOrder>();
				orderedMenu = new String[menuOrders.size()];
				orderedList.setListData(orderedMenu);
			}
		});
		menuManagement[2] = new JButton("-");
		menuManagement[2].setBackground(Color.WHITE);
		menuManagement[2].setForeground(Color.DARK_GRAY);
		menuManagement[2].setFont(new Font("돋움",Font.PLAIN,12));
		menuManagement[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < menuOrders.size(); j++) {
					if (menuOrders.get(j).getMenu().getMenuName().equals(selectList)) {
						menuOrders.get(j).setCount(menuOrders.get(j).getCount() - 1);
						menuOrders.get(j)
								.setPrice(menuOrders.get(j).getCount() * menuOrders.get(j).getMenu().getPrice());
						if (menuOrders.get(j).getCount() == 0) {
							menuOrders.remove(j);
						}
					}
				}
				orderedMenu = new String[menuOrders.size()];
				for (int i = 0; i < orderedMenu.length; i++) {
					orderedMenu[i] = new String("    " + menuOrders.get(i).getMenu().getMenuName() + "          "
							+ menuOrders.get(i).getCount() + "          " + menuOrders.get(i).getPrice());

				}
				orderedList.setListData(orderedMenu);
			}

		});
		menuManagement[3] = new JButton("+");
		menuManagement[3].setBackground(Color.WHITE);
		menuManagement[3].setForeground(Color.DARK_GRAY);
		menuManagement[3].setFont(new Font("돋움",Font.PLAIN,12));
		menuManagement[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int j = 0; j < menuOrders.size(); j++) {
					if (menuOrders.get(j).getMenu().getMenuName().equals(selectList)) {
						menuOrders.get(j).setCount(menuOrders.get(j).getCount() + 1);
						menuOrders.get(j)
								.setPrice(menuOrders.get(j).getCount() * menuOrders.get(j).getMenu().getPrice());
					}
				}

				orderedMenu = new String[menuOrders.size()];
				for (int i = 0; i < orderedMenu.length; i++) {
					orderedMenu[i] = new String("    " + menuOrders.get(i).getMenu().getMenuName() + "          "
							+ menuOrders.get(i).getCount() + "          " + menuOrders.get(i).getPrice());

				}
				orderedList.setListData(orderedMenu);
			}

		});

		for (int i = 0; i < menuManagement.length; i++) {
			menuManagement[i].setFont(new Font("돋음", Font.PLAIN, 15));
			pManagement.add(menuManagement[i]);
		}

		this.setVisible(true);

	}

	class NumberInsert implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (moneyToReceiveT.hasFocus()) {
				moneyToReceiveT.setText(moneyToReceiveT.getText() + e.getActionCommand());
			} else if (moneyReceivedT.hasFocus()) {
				moneyReceivedT.setText(moneyReceivedT.getText() + e.getActionCommand());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("카드")) {
			io.removeObject("TableOrder", tableOrder);
			remain -= Integer.parseInt(moneyToReceiveT.getText());
			moneyToReceiveT.setText(String.valueOf(remain));
			tableOrder.setRemain(remain);
			io.editDB("TableOrder", tableOrder);

			moneyReceivedT.setText("");
			ChangeT.setText("");
		} else {
			daySale.setCash(daySale.getCash() + Integer.parseInt(moneyToReceiveT.getText()));
			// if(moneyReceivedT.getText().equals("")){
			// }
			int change = 0;
			try {
				change = Integer.parseInt(moneyReceivedT.getText()) - Integer.parseInt(moneyToReceiveT.getText());
				if (change < 0) {
					remain = change * -1;
				} else {
					remain -= Integer.parseInt(moneyToReceiveT.getText());
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "받은금액을 입력하세요");

			}
			ChangeT.setText(String.valueOf(change));

			io.removeObject("TableOrder", tableOrder);
			moneyToReceiveT.setText(String.valueOf(remain));
			tableOrder.setRemain(remain);
			io.editDB("TableOrder", tableOrder);
		}
		if (remain == 0) {
			new BillPage(tableNo, this);

			daySale.setTotalSales(daySale.getTotalSales() + tableOrder.getTotalcost());
			io.removeObject("TableOrder", tableOrder);
			menuOrders = new ArrayList<MenuOrder>();

			daySales.add(daySale);
			io.writeDB("DaySales", daySales);

			tableFrame.paySuccess(tableNo);
		}
	}
}
