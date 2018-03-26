package mainFrame;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import checkPassword.CheckPasswordMethod;
import fileIO.FileIO;
import graphics.ManagementPage;
import graphics.ViewTables;
import miniProject.db.DaySales;
import miniProject.run.Run;

public class MainFrame {
	private JButton salesManagementBtn;
	private JButton openStoreBtn;
	private JButton storeManagementBtn;
	private JButton exitBtn;
	private JButton backBtn;
	private JFrame mainFrame;
	Run salesM;

	FileIO io = new FileIO();
	CheckPasswordMethod check = new CheckPasswordMethod();

	public MainFrame() {
		
		
		
		
		mainFrame = new JFrame("UPS_POS System");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(0, 0, 1200, 900);
		mainFrame.setLayout(null);
		
		Image backIcon = new ImageIcon("food.jpg").getImage().getScaledInstance(600, 900, 0);
		backBtn = new JButton(new ImageIcon(backIcon));
		backBtn.setBounds(0,0,600,900);
		mainFrame.add(backBtn);
		
		Image salesIcon = new ImageIcon("chart.png").getImage().getScaledInstance(200, 250, 0);
		salesManagementBtn = new JButton(new ImageIcon(salesIcon));
		salesManagementBtn.setBackground(Color.WHITE);
		salesManagementBtn.setBounds(600, 0, 300, 450);
		mainFrame.add(salesManagementBtn);
		salesManagementBtn.addActionListener(new MainFrameEvent());
		Image openIcon = new ImageIcon("menu.png").getImage().getScaledInstance(200, 250, 0);
		openStoreBtn = new JButton(new ImageIcon(openIcon));
		openStoreBtn.setBackground(Color.LIGHT_GRAY);
		openStoreBtn.setBounds(900, 0, 300, 450);
		mainFrame.add(openStoreBtn);
		openStoreBtn.addActionListener(new MainFrameEvent());
		Image storeIcon = new ImageIcon("document.png").getImage().getScaledInstance(200, 250, 0);
		storeManagementBtn = new JButton(new ImageIcon(storeIcon));
		storeManagementBtn.setBackground(Color.DARK_GRAY);
		storeManagementBtn.setBounds(600, 450, 300, 450);
		mainFrame.add(storeManagementBtn);
		storeManagementBtn.addActionListener(new MainFrameEvent());
		Image exitIcon = new ImageIcon("power.png").getImage().getScaledInstance(200, 250, 0);
		exitBtn = new JButton(new ImageIcon(exitIcon));
		exitBtn.setBackground(Color.WHITE);
		exitBtn.setBounds(900, 450, 300, 450);
		mainFrame.add(exitBtn);
		exitBtn.addActionListener(new MainFrameEvent());
		mainFrame.setVisible(true);
		
		check.ChekingMethod();

		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		String date = ft.format(new Date());
		ArrayList<DaySales> daySales = (ArrayList<DaySales>) io.readDB("DaySales");
		int re = 0;
		for (int i = 0; i < daySales.size(); i++) {
			if (daySales.get(i).getDate().equals(date)) {
				re = -1;
			}
		}
		if (re == 0) {
			io.editDB("DaySales", new DaySales(date, 0, 0, 0));
		}
	}

	class MainFrameEvent implements ActionListener {
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == salesManagementBtn) {
					salesM = new Run();
			}
			if (e.getSource() == openStoreBtn) {
				new ViewTables();
			}
			if (e.getSource() == storeManagementBtn) {
				new ManagementPage();
			}
			if (e.getSource() == exitBtn) {
				exitBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);

					}
				});
			}
		}
	}

}
