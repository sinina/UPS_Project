package miniProject.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import checkPassword.CheckPasswordMethod;
import fileIO.FileIO;
import miniProject.db.DaySales;

public class Magam extends Panel {

	public JButton getsIn() {
		return sIn;
	}

	public JTextField[] getJtf() {
		return jtf;
	}

	public void setJtf(JTextField[] jtf) {
		this.jtf[8].setText("0 원");
		for (int i = 0; i < jtf.length; i++) {
			this.jtf[i].setText(jtf[i].getText());
			StringTokenizer st = new StringTokenizer(this.jtf[8].getText(), " ");
			String str = this.jtf[i].getText();
			if (!str.equals("")) {
				this.jtf[8].setText(
						Integer.parseInt(st.nextToken()) + addto(i, Integer.parseInt(this.jtf[i].getText())) + " 원");
			}
		}

	}

	JButton[] btn;
	JTextField[] jtf;
	String[] num = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "00", "<-" };
	JButton sIn;
	JTabbedPane ui;

	FileIO io = new FileIO();

	public Magam() {
		this.setSize(1200, 900);
		this.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(600, 250, 450, 500);
		panel.setLayout(new GridLayout(4, 3, 30, 30));

		JPanel panel1 = new JPanel();
		panel1.setBounds(100, 250, 400, 500);
		panel1.setLayout(new GridLayout(9, 2, 20, 20));

		JLabel title = new JLabel("마   감");
		title.setBounds(200, 50, 300, 100);
		title.setFont(new Font("돋움", 0, 50));
		this.add(title);

		sIn = new JButton("승  인");
		sIn.setBounds(700, 80, 160, 100);
		sIn.setBackground(Color.WHITE);
		sIn.setFont(new Font("돋움", Font.BOLD, 14));
		this.add(sIn);

		JButton clear = new JButton("정  정");
		clear.setBounds(890, 80, 160, 100);
		clear.setBackground(Color.WHITE);
		clear.setFont(new Font("돋움", Font.BOLD, 14));
		this.add(clear);

		String[] won = { "오 만 원", "만      원", "오 천 원", "천      원", "오 백 원", "백      원", "오 십 원", "십      원",
				"총      합" };
		JLabel[] labels = new JLabel[won.length];
		jtf = new JTextField[won.length];

		for (int i = 0; i < won.length; i++) {
			labels[i] = new JLabel(won[i]);
			panel1.add(labels[i]);
			jtf[i] = new JTextField(30);
			jtf[i].addKeyListener(new TextFiledHandler()) ;
				
		
			jtf[i].setFont(new Font("돋움", Font.PLAIN, 12));
			panel1.add(jtf[i]);
		}
		jtf[8].setText("0 원");
		jtf[8].setEditable(false);

		btn = new JButton[num.length];
		for (int i = 0; i < num.length; i++) {
			btn[i] = new JButton(num[i]);
			btn[i].setFocusable(false);
			btn[i].setBackground(Color.GRAY);
			btn[i].setForeground(Color.WHITE);
			btn[i].setFont(new Font("돋움", Font.BOLD, 14));
			panel.add(btn[i]);
		}

		for (int i = 0; i < btn.length; i++) {
			btn[i].addActionListener(new buttonHandler());
		}

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < jtf.length - 1; i++) {
					jtf[i].setText("");
				}
				jtf[8].setText("0 원");

			}
		});

		sIn.addActionListener(new ActionListener() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			@Override
			public void actionPerformed(ActionEvent e) {

				String st = new String(jtf[8].getText());
				String[] strA = st.split(" ");
				DaySales ds = new DaySales();
				int totalSales = 0;
				int expenses = 0;
				ArrayList<DaySales> daySales = (ArrayList<DaySales>) io.readDB("DaySales");
				for (int i = 0; i < daySales.size(); i++) {
					if (daySales.get(i).getDate().equals(sdf.format(date))) {
						ds = daySales.get(i);
						if (totalSales == 0 && expenses == 0) {
							totalSales = ds.getTotalSales();
							expenses = ds.getExpenses();
						}
						io.removeObject("DaySales", daySales.get(i));
					}
				}

				io.editDB("DaySales", new DaySales(sdf.format(date), totalSales, expenses, Integer.parseInt(strA[0])));

				totalSales = 0;
				expenses = 0;

				ui.setSelectedIndex(2);
			}
		});

		this.add(panel);
		this.add(panel1);

	}

	class buttonHandler implements ActionListener {
		StringTokenizer st;
		String str;
		int number;

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < btn.length - 1; i++) {
				if (e.getActionCommand().equals(num[i]) && Integer.parseInt(num[i]) >= 1) {
					for (int k = 0; k < jtf.length; k++) {
						if (jtf[k].hasFocus()) {
							jtf[k].setText(jtf[k].getText() + Integer.parseInt(num[i]));
							st = new StringTokenizer(jtf[8].getText(), " ");
							str = jtf[k].getText();
							number = str.substring(0, str.length() - 1).equals("") ? 0
									: Integer.parseInt(str.substring(0, str.length() - 1));
							jtf[8].setText(Integer.parseInt(st.nextToken())
									+ addto(k, Integer.parseInt(jtf[k].getText()) - number) + " 원");
						}
					}
				} else if (e.getActionCommand().equals(num[i]) && num[i] == "0") {
					for (int k = 0; k < jtf.length; k++) {
						if (jtf[k].hasFocus()) {
							jtf[k].setText(jtf[k].getText() + "0");
							st = new StringTokenizer(jtf[8].getText(), " ");
							str = jtf[k].getText();
							number = str.substring(0, str.length() - 1).equals("") ? 0
									: Integer.parseInt(str.substring(0, str.length() - 1));
							jtf[8].setText(Integer.parseInt(st.nextToken())
									+ addto(k, Integer.parseInt(jtf[k].getText()) - number) + " 원");
						}
					}
				} else if (e.getActionCommand().equals(num[i])) {
					for (int k = 0; k < jtf.length; k++) {
						if (jtf[k].hasFocus())
							if (jtf[k].hasFocus()) {
								jtf[k].setText(jtf[k].getText() + "00");
								st = new StringTokenizer(jtf[8].getText(), " ");
								str = jtf[k].getText();
								number = str.substring(0, str.length() - 2).equals("") ? 0
										: Integer.parseInt(str.substring(0, str.length() - 2));
								jtf[8].setText(Integer.parseInt(st.nextToken())
										+ addto(k, Integer.parseInt(jtf[k].getText()) - number) + " 원");
							}
					}
				}
			}
			if (e.getActionCommand().equals(num[11])) {
				for (int k = 0; k < jtf.length; k++) {
					if (jtf[k].hasFocus() && jtf[k].getSelectionEnd() != 0) {
						str = jtf[k].getText();
						jtf[k].setText(jtf[k].getText().substring(0, jtf[k].getSelectionEnd() - 1));
						st = new StringTokenizer(jtf[8].getText(), " ");
						number = str.substring(0, str.length() - 1).equals("") ? 0
								: Integer.parseInt(str.substring(0, str.length() - 1));
						jtf[8].setText(
								(Integer.parseInt(st.nextToken()) - addto(k, Integer.parseInt(str) - number)) + " 원");
					}
				}
			}
		}

	}

	private int addto(int index, int count) {
		int sum = 0;
		switch (index) {
		case 0:
			sum += (count * 50000);
			break;
		case 1:
			sum += (count * 10000);
			break;
		case 2:
			sum += (count * 5000);
			break;
		case 3:
			sum += (count * 1000);
			break;
		case 4:
			sum += (count * 500);
			break;
		case 5:
			sum += (count * 100);
			break;
		case 6:
			sum += (count * 50);
			break;
		case 7:
			sum += (count * 10);
			break;
		default:
			System.out.println("머냥");
		}
		return sum;

	}

	public void test(JTabbedPane ui) {
		this.ui = ui;
	}
	class TextFiledHandler implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
//			char c = e.getKeyChar();
//			if (!((Character.isDigit(c) || 
//			(c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
			getToolkit().beep();
			e.consume();
//			}else{
//				System.out.println(c);
//			}
		}
		
	}
}
