package miniProject.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileIO.FileIO;
import miniProject.db.DaySales;

public class Today extends Panel {
	JTextField suicTF;
	JTextField soonTF;
	JTextField ugTF;
	JTextField moneyTF;

	FileIO io = new FileIO();

	public Today() {

		this.setSize(1200, 900);
		this.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(400, 260, 400, 400);
		panel.setLayout(new GridLayout(4, 2, 0, 50));

		JLabel title = new JLabel("금일 순매출");
		title.setBounds(480, 120, 600, 50);
		title.setFont(new Font("돋움", Font.BOLD, 40));
		this.add(title);

		JLabel suic = new JLabel("총 수익");
		suic.setFont(new Font("돋움", Font.BOLD, 16));
		panel.add(suic);
		suicTF = new JTextField(30);
		suicTF.setEditable(false);
		panel.add(suicTF);

		JLabel soon = new JLabel("순 수익");
		soon.setFont(new Font("돋움", Font.BOLD, 16));
		panel.add(soon);
		soonTF = new JTextField(30);
		soonTF.setEditable(false);
		panel.add(soonTF);

		JLabel ug = new JLabel("유지비");
		ug.setFont(new Font("돋움", Font.BOLD, 16));
		panel.add(ug);
		ugTF = new JTextField(30);
		ugTF.setEditable(false);
		panel.add(ugTF);

		JLabel money = new JLabel("현금");
		money.setFont(new Font("돋움", Font.BOLD, 16));
		panel.add(money);
		moneyTF = new JTextField(30);
		moneyTF.setEditable(false);
		panel.add(moneyTF);

		this.add(panel);
		this.setVisible(true);

	}

	public void getToday() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		DaySales ds = new DaySales();

		ArrayList<DaySales> daySales = (ArrayList<DaySales>) io.readDB("DaySales");
		for (int i = 0; i < daySales.size(); i++) {

			if (daySales.get(i).getDate().equals(sdf.format(date))) {
				ds = daySales.get(i);
			}
		}

		suicTF.setText(String.valueOf(ds.getTotalSales()));
		suicTF.setFont(new Font("돋움", Font.BOLD, 16));
		suicTF.setForeground(Color.BLACK);

		soonTF.setText(String.valueOf(ds.getSoonSales()));
		soonTF.setFont(new Font("돋움", Font.BOLD, 16));
		soonTF.setForeground(Color.BLACK);
		
		ugTF.setText(String.valueOf(ds.getExpenses()));
		ugTF.setFont(new Font("돋움", Font.BOLD, 16));
		ugTF.setForeground(Color.BLACK);
		
		moneyTF.setText(String.valueOf(ds.getCash()));
		moneyTF.setFont(new Font("돋움", Font.BOLD, 16));
		moneyTF.setForeground(Color.BLACK);

	}

}
