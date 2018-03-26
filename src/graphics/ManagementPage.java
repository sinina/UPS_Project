package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fileIO.FileIO;

public class ManagementPage extends JFrame {
	Panel editmenu = new Panel();
	Panel addtable = new Panel();
	Panel deletetable = new Panel();
	JTabbedPane tabbedPane = new JTabbedPane();
	
	FileIO io = new FileIO();
	
	public ManagementPage() {
		setBounds(0, 0, 1200, 900);
		setTitle("관리");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(null);
		
		MenuList sl = new MenuList();
		editmenu.add(sl);
		SetTable st = new SetTable();
		addtable.add(st);
		DeleteTable dt = new DeleteTable();
		deletetable.add(dt);
		
		tabbedPane.setBackground(Color.getColor("#CCCCCC"));
		tabbedPane.setSize(1182, 860);
		tabbedPane.setFont(new Font("돋음", Font.PLAIN, 15));
		tabbedPane.add("메뉴관리", editmenu);
		tabbedPane.add("테이블 추가", addtable);
		tabbedPane.add("테이블 삭제", deletetable);
		
		tabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(tabbedPane.getSelectedIndex()){
				case 0:
//					System.out.println("메뉴관리 선택");
					sl.repaint();
					
					break;
				case 1:
//					addtable.removeAll();
					st.addTableButton();
					addtable.add(st);
					break;
				case 2:
//					deletetable.removeAll();
					dt.addTableButton();
					deletetable.add(dt);
					break;
				}
				
			}
		});
		
		add(tabbedPane);
		
		this.setVisible(true);
	}
}
