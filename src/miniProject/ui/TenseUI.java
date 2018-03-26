package miniProject.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fileIO.FileIO;
import miniProject.db.DaySales;

public class TenseUI extends Panel {

	public JTextField[] getjtextField() {
		return jtextField;
	}

	public void setjtextField(JTextField[] jtextField) {
		this.jtextField = jtextField.clone();
	}

	public JLabel getCurrentTensedala() {
		return currentTensedala;
	}

	private JPanel jbtnPanel;
	private JPanel tenseInputPanel;
	private JPanel currentTense;
	private JPanel subTitlePanel;
	private JTextField[] jtextField;
	private JButton[] jbtn;
	private String[] jbtn_Title = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "00", "<-" };
	private JLabel currentTensedala;

	FileIO io = new FileIO();

	public TenseUI() {
		inIt();
	}

	public void inIt() {
		this.setSize(1200, 900);
		setting();
		prepareGUI();
	}

	private void setting() {

		jbtnPanel = new JPanel();
		tenseInputPanel = new JPanel();
		currentTense = new JPanel();
		subTitlePanel = new JPanel();
		
		showButton();
		showInputScreen();
		showCurrentTense();
		subTitle();
	}

	private void prepareGUI() {

		this.setLayout(null);
		this.add(tenseInputPanel).setBounds(270, 200, 280, 500);
		this.add(jbtnPanel).setBounds(630, 200, 400, 400);
		this.add(currentTense).setBounds(750, 670, 150, 150);
		this.add(subTitlePanel).setBounds(480,70,200,100);
	}

	private void showButton() {

		jbtn = new JButton[jbtn_Title.length];

		jbtnPanel.setLayout(new GridLayout(4, 3, 20, 20));

		for (int i = 0; i < jbtn_Title.length; i++) {

			jbtnPanel.add(jbtn[i] = new JButton(jbtn_Title[i]));
			jbtn[i].setFocusable(false);

			jbtn[i].addActionListener(new buttonHandler());

			jbtn[i].setFont(new Font("돋움", Font.BOLD, 12));
			jbtn[i].setBackground(Color.GRAY);
			jbtn[i].setForeground(Color.WHITE);
		}

	}

	private void showInputScreen() {
		String[] label_Text = { "오 만 원", "만      원", "오 천 원", "천      원", "오 백 원", "백      원", "오 십 원", "십      원" };
		JLabel[] jlabel = new JLabel[label_Text.length];
		jtextField = new JTextField[label_Text.length];

		JPanel left = new JPanel();
		JPanel right = new JPanel();

		left.setLayout(new GridLayout(0, 1, 0, 20));
		right.setLayout(new GridLayout(0, 1, 0, 20));

		for (int i = 0; i < label_Text.length; i++) {
			left.add(jlabel[i] = new JLabel(label_Text[i]));
			right.add(jtextField[i] = new JTextField(10));
		}
		tenseInputPanel.setLayout(new GridLayout(1, 2));
		tenseInputPanel.add(left);
		tenseInputPanel.add(right);

	}

	private void showCurrentTense() {
		JLabel currentTensela = new JLabel("현재 시재");
		currentTensela.setFont(new Font("돋움", Font.BOLD, 16));

		currentTensedala = new JLabel("0 원");
		getToday();
		currentTense.add(currentTensela);
		currentTense.add(currentTensedala);

		currentTensedala.setFont(new Font("돋움", Font.BOLD, 16));

	}

	private void subTitle() {
		JLabel subTitlelabel = new JLabel("중간 점검");
		subTitlelabel.setFont(new Font("돋움", Font.BOLD, 40));
		subTitlePanel.add(subTitlelabel);
	}

	class buttonHandler implements ActionListener {
		StringTokenizer st, st2;
		String str;
		int num;

		@Override
		public void actionPerformed(ActionEvent e) {

			for (int i = 0; i < jbtn.length - 1; i++) {
				if (e.getActionCommand().equals(jbtn_Title[i]) && Integer.parseInt(jbtn_Title[i]) >= 1) {

					for (int k = 0; k < jtextField.length; k++) {
						if (jtextField[k].hasFocus()) {
							jtextField[k].setText(jtextField[k].getText() + Integer.parseInt(jbtn_Title[i]));

							st = new StringTokenizer(currentTensedala.getText(), " ");
							str = jtextField[k].getText();
							num = str.substring(0, str.length() - 1).equals("") ? 0
									: Integer.parseInt(str.substring(0, str.length() - 1));
							currentTensedala.setText(Integer.parseInt(st.nextToken())
									+ add(k, Integer.parseInt(jtextField[k].getText()) - num) + " 원");

						}

					}
				} else if (e.getActionCommand().equals(jbtn_Title[i]) && jbtn_Title[i] == "0") {
					for (int k = 0; k < jtextField.length; k++) {
						if (jtextField[k].hasFocus()) {
							jtextField[k].setText(jtextField[k].getText() + "0");
							st = new StringTokenizer(currentTensedala.getText(), " ");
							str = jtextField[k].getText();
							num = str.substring(0, str.length() - 1).equals("") ? 0
									: Integer.parseInt(str.substring(0, str.length() - 1));
							currentTensedala.setText(Integer.parseInt(st.nextToken())
									+ add(k, Integer.parseInt(jtextField[k].getText()) - num) + " 원");
						}

					}
				} else if (e.getActionCommand().equals(jbtn_Title[i])) {
					for (int k = 0; k < jtextField.length; k++) {
						if (jtextField[k].hasFocus()) {
							jtextField[k].setText(jtextField[k].getText() + "00");
							st = new StringTokenizer(currentTensedala.getText(), " ");
							str = jtextField[k].getText();
							num = str.substring(0, str.length() - 2).equals("") ? 0
									: Integer.parseInt(str.substring(0, str.length() - 2));
							currentTensedala.setText(Integer.parseInt(st.nextToken())
									+ add(k, Integer.parseInt(jtextField[k].getText()) - num) + " 원");
						}

					}
				}
			}
			if (e.getActionCommand().equals(jbtn_Title[11])) {
				for (int k = 0; k < jtextField.length; k++) {
					if (jtextField[k].hasFocus() && jtextField[k].getSelectionEnd() != 0) {
						str = jtextField[k].getText();
						jtextField[k]
								.setText(jtextField[k].getText().substring(0, jtextField[k].getSelectionEnd() - 1));
						st = new StringTokenizer(currentTensedala.getText(), " ");

						num = str.substring(0, str.length() - 1).equals("") ? 0
								: Integer.parseInt(str.substring(0, str.length() - 1));

						currentTensedala.setText(
								(Integer.parseInt(st.nextToken()) - add(k, Integer.parseInt(str) - num)) + " 원");
					}
				}
			}
		}

		private int add(int index, int count) {
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
//		System.out.println(ds.getCash());
//		System.out.println(ds.getDate());
//		System.out.println(ds.getExpenses());
//		System.out.println(ds.getSoonSales());
//		System.out.println(ds.getTotalSales());
		currentTensedala.setText(String.valueOf(-ds.getCash()));

	}
}
