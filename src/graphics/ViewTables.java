package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fileIO.FileIO;
import miniProject.db.TableLocation;
import miniProject.db.TableOrder;

public class ViewTables extends JFrame implements ActionListener {
	ArrayList<TableLocation> tables = null;
	Panel pCenter;
	ArrayList<JButton> tableButtons = new ArrayList<JButton>();
	ArrayList<TableOrder> tableOrders = null;

	FileIO io = new FileIO();

	JLabel tableCount;
	@SuppressWarnings("unchecked")
	public ViewTables() {
		paintJFrame();

	}

	
	public void paintJFrame(){
		setBounds(0, 0, 1200, 900);
		setTitle("테이블 목록");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);

		Panel pNorth = new Panel();
		Panel pCenter = new Panel();
		pNorth.setSize(1200, 70);
		pCenter.setBounds(0, 70, 1200, 830);
		this.add(pNorth);
		this.add(pCenter);
		pNorth.setLayout(null);
		pCenter.setLayout(null);

		this.relodeDB();

		tableCount = new JLabel(String.valueOf(tableOrders.size()) + " / " + String.valueOf(tables.size()));
		tableCount.setBounds(570, 10, 1000, 40);
		tableCount.setFont(new Font("돋음", Font.PLAIN, 20));
		pNorth.add(tableCount);

		for (int i = 0; i < tables.size(); i++) {
			tableButtons.add(new JButton(String.valueOf(tables.get(i).getTableNo())));
			tableButtons.get(i).setHorizontalAlignment(SwingConstants.LEFT);
			tableButtons.get(i).setVerticalAlignment(SwingConstants.TOP);
			tableButtons.get(i).setSize(200, 150);
			tableButtons.get(i).setLocation(tables.get(i).getX(), tables.get(i).getY());
			tableButtons.get(i).setBackground(Color.WHITE);
			tableButtons.get(i).setForeground(Color.DARK_GRAY);
			tableButtons.get(i).setFont(new Font("돋움", Font.BOLD, 14));

			tableButtons.get(i).addActionListener(this);
			for (int j = 0; j < tableOrders.size(); j++) {
				if (tableOrders.get(j).getTableNo() == tables.get(i).getTableNo()) {
					tableButtons.get(i).setBackground(Color.LIGHT_GRAY);
//					tableButtons.get(i).setFont(new Font());
				}
			}

			pCenter.add(tableButtons.get(i));
		}

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OrderPage p = new OrderPage(Integer.parseInt(e.getActionCommand()), this);
	}
	
	public void relodeDB() {
		tables = (ArrayList<TableLocation>) io.readDB("tableLocation");
		tableOrders = io.readDB("TableOrder");
//		this.repaint();
	}
	
	public void orderSuccess(int tableNum){
		relodeDB();
		JButton tempBtn = tableButtons.get(tableNum-1);
		tempBtn.setBackground(Color.lightGray);
		tableCount.setText(String.valueOf(tableOrders.size()) + " / " + String.valueOf(tables.size()));
	}
	
	public void paySuccess(int tableNum){
		relodeDB();
		JButton tempBtn = tableButtons.get(tableNum-1);
		tempBtn.setBackground(Color.white);
		tableCount.setText(String.valueOf(tableOrders.size()) + " / " + String.valueOf(tables.size()));
	}
}
