package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fileIO.FileIO;
import miniProject.db.Menu;

public class MenuList extends Panel {

	String menuCategory;
	String menuName;
	int menuPrice;
	// ObjectStreamRun run;
	Menu menu;
	JList menuList;

	ArrayList<Menu> menus;

	HashMap<String, Menu> menuMap = new HashMap<String, Menu>();
	FileIO io = new FileIO();

	String str ="                                                                                               ";
	public MenuList() {

		// ArrayList<Menu> menus = new ArrayList<Menu>();
		/*
		 * JFrame menuListFrame = new JFrame();
		 * 
		 * menuListFrame.setTitle("메뉴"); menuListFrame.setResizable(false);
		 * menuListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 */
		this.setBounds(0, 0, 1200, 900);
		this.setLayout(null);

		JLabel categoryLabel = new JLabel("카테고리");
		categoryLabel.setFont(new Font("돋움", Font.BOLD, 12));
		JLabel menuNameLabel = new JLabel("메뉴");
		menuNameLabel.setFont(new Font("돋움", Font.BOLD, 12));
		JLabel priceLabel = new JLabel("가격");
		priceLabel.setFont(new Font("돋움", Font.BOLD, 12));

		JPanel labels = new JPanel();

		labels.add(categoryLabel);
		labels.add(menuNameLabel);
		labels.add(priceLabel);
		labels.setLayout(new GridLayout(1, 3));

		// this.add(labels, "North");
		this.add(labels);
		labels.setBounds(15, 0, 1170, 30);

		menuList = new JList();

		reviewPage();

		menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		menuList.setLayoutOrientation(JList.VERTICAL);
		menuList.setFont(new Font("돋움", Font.PLAIN, 12));

		JScrollPane menuScrollPane = new JScrollPane(menuList);

		// this.add(menuScrollPane, "Center");
		menuScrollPane.setBounds(15, 30, 1170, 750);
		this.add(menuScrollPane);

		JPanel modifyMenuButtons = new JPanel();

		JButton addMenuButton = new JButton("메뉴 추가");
		addMenuButton.setFont(new Font("돋움", Font.BOLD, 14));
		addMenuButton.setBackground(Color.WHITE);
		addMenuButton.setForeground(Color.DARK_GRAY);
		JButton changeMenuButton = new JButton("메뉴 수정");
		changeMenuButton.setFont(new Font("돋움", Font.BOLD, 14));
		changeMenuButton.setBackground(Color.WHITE);
		changeMenuButton.setForeground(Color.DARK_GRAY);
		JButton deleteMenuButton = new JButton("메뉴 삭제");
		deleteMenuButton.setFont(new Font("돋움", Font.BOLD, 14));
		deleteMenuButton.setBackground(Color.WHITE);
		deleteMenuButton.setForeground(Color.DARK_GRAY);

		addMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField newMenuNameField = new JTextField(10);
				JTextField newMenuPriceField = new JTextField(10);

				ArrayList<Menu> ms = (ArrayList<Menu>) io.readDB("menu");
				for (int i = 0; i < ms.size(); i++) {
					menuMap.put(ms.get(i).getMenuName(), ms.get(i));
				}

				HashSet<String> category = new HashSet<String>();

				Set<Entry<String, Menu>> entry = menuMap.entrySet();
				Iterator<Entry<String, Menu>> entryIter = entry.iterator();
				Entry<String, Menu> temp = null;

				while (entryIter.hasNext()) {
					temp = entryIter.next();
					category.add(temp.getValue().getCategory());
				}

				Iterator<String> categoryIter = category.iterator();

				String[] categories = category.toArray(new String[category.size()]);

				JComboBox categoryBox = new JComboBox(categories);
				JTextField categoryField = new JTextField(10);
				categoryBox.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String categoryChoice = (String) categoryBox.getSelectedItem();
						categoryField.setText(categoryChoice);

					}
				});

				JLabel addCategoryLabel = new JLabel("카테고리");
				JLabel newMenuNameLabel = new JLabel("메뉴이름");
				JLabel newMenuPriceLabel = new JLabel("메뉴가격");

				JPanel addMenuPanel = new JPanel();
				addMenuPanel.add(newMenuNameLabel);
				addMenuPanel.add(newMenuNameField);
				addMenuPanel.add(newMenuPriceLabel);
				addMenuPanel.add(newMenuPriceField);
				addMenuPanel.add(addCategoryLabel);
				addMenuPanel.add(categoryBox);
				addMenuPanel.add(categoryField);
				addMenuPanel.setLayout(new BoxLayout(addMenuPanel, BoxLayout.Y_AXIS));

				int result = JOptionPane.showConfirmDialog(null, addMenuPanel, "메뉴 추가", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION && categoryField.getText() != null) {
					io.editDB("menu", new Menu(newMenuNameField.getText(),
							Integer.parseInt(newMenuPriceField.getText()), categoryField.getText()));
					reviewPage();

				} else if (result == JOptionPane.OK_OPTION && categoryField.getText().equals("")) {
					io.editDB("menu", new Menu(newMenuNameField.getText(),
							Integer.parseInt(newMenuPriceField.getText()), (String) categoryBox.getSelectedItem()));
					reviewPage();
				}

			}
		});

		changeMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JTextField changeMenuNameField = new JTextField(10);
				JTextField changeMenuPriceField = new JTextField(10);
				int size = menus.size();

				ArrayList<Menu> ms = (ArrayList<Menu>) io.readDB("menu");
				for (int i = 0; i < ms.size(); i++) {
					menuMap.put(ms.get(i).getMenuName(), ms.get(i));
				}

				HashSet<String> category = new HashSet<String>();

				Set<Entry<String, Menu>> entry = menuMap.entrySet();
				Iterator<Entry<String, Menu>> entryIter = entry.iterator();
				Entry<String, Menu> temp = null;

				while (entryIter.hasNext()) {
					temp = entryIter.next();
					category.add(temp.getValue().getCategory());
				}

				Iterator<String> categoryIter = category.iterator();

				String[] categories = category.toArray(new String[category.size()]);

				JComboBox categoryBox = new JComboBox(categories);
				JTextField categoryField = new JTextField(10);
				categoryBox.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String categoryChoice = (String) categoryBox.getSelectedItem();
						categoryField.setText(categoryChoice);

					}
				});

				JLabel changeCategoryLabel = new JLabel("카테고리");
				JLabel changeMenuNameLabel = new JLabel("메뉴이름");
				JLabel changeMenuPriceLabel = new JLabel("메뉴가격");

				String[] tempStr = menuList.getSelectedValue().toString().split(str);

				JPanel changeMenuPanel = new JPanel();
				changeMenuPanel.add(changeCategoryLabel);
				changeMenuPanel.add(categoryBox);
				changeMenuPanel.add(categoryField);
				categoryField.setText(tempStr[0]);
				changeMenuPanel.add(changeMenuNameLabel);
				changeMenuPanel.add(changeMenuNameField);
				changeMenuNameField.setText(tempStr[1]);
				changeMenuPanel.add(changeMenuPriceLabel);
				changeMenuPanel.add(changeMenuPriceField);
				changeMenuPriceField.setText(tempStr[2]);
				changeMenuPanel.setLayout(new BoxLayout(changeMenuPanel, BoxLayout.Y_AXIS));

				int result = JOptionPane.showConfirmDialog(null, changeMenuPanel, "메뉴 수정", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION && categoryField != null) {
					String[] tempStrArr = menuList.getSelectedValue().toString().split(str);
					String tempString = tempStrArr[1] + "," + tempStrArr[2] + "," + tempStrArr[0];
					for (int i = 0; i < menus.size(); i++) {
						if (menus.get(i).toString().equals(tempString)) {
							io.removeObject("menu", menus.get(i));
						}
					}
					io.editDB("menu", new Menu(changeMenuNameField.getText(),
							Integer.parseInt(changeMenuPriceField.getText()), categoryField.getText()));
					reviewPage();

				} else if (result == JOptionPane.OK_OPTION && categoryField.getText().equals("")) {
					String[] tempStrArr = menuList.getSelectedValue().toString().split(str);
					String tempString = tempStrArr[1] + "," + tempStrArr[2] + "," + tempStrArr[0];
					for (int i = 0; i < menus.size(); i++) {
						if (menus.get(i).toString().equals(tempString)) {
							io.removeObject("menu", menus.get(i));
						}
					}
					String categoryString = (String) categoryBox.getSelectedItem();

					io.editDB("menu", new Menu(changeMenuNameField.getText(),
							Integer.parseInt(changeMenuPriceField.getText()), (String) categoryBox.getSelectedItem()));
					reviewPage();
				}
			}
		});

		deleteMenuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[] tempStrArr = menuList.getSelectedValue().toString().split(str);
				String tempString = tempStrArr[1] + "," + tempStrArr[2] + "," + tempStrArr[0];
				for (int i = 0; i < menus.size(); i++) {
					if (menus.get(i).toString().equals(tempString)) {
						io.removeObject("menu", menus.get(i));
						reviewPage();
					}
				}

			}
		});

		modifyMenuButtons.setLayout(new GridLayout(1, 3));
		modifyMenuButtons.add(addMenuButton);
		modifyMenuButtons.add(changeMenuButton);
		modifyMenuButtons.add(deleteMenuButton);
		// this.add(modifyMenuButtons, "South");
		modifyMenuButtons.setBounds(15, 780, 1170, 50);
		this.add(modifyMenuButtons);

		this.setVisible(true);

	}

	public void reviewPage() {
		menus = io.readDB("menu");

		String[] menustr = new String[menus.size()];


		for (int i = 0; i < menustr.length; i++) {

			menustr[i] = menus.get(i).getCategory() + str + menus.get(i).getMenuName() + str + menus.get(i).getPrice();
		}

		menuList.setListData(menustr);
	}

}
