package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import fileIO.FileIO;
import miniProject.db.TableLocation;

public class DeleteTable extends Panel implements ActionListener{
	ArrayList<TableLocation> tables = new ArrayList<TableLocation>();
	Panel pCenter;
	ArrayList<JButton> tableButtons = new ArrayList<JButton>();

	FileIO io = new FileIO();
	
	public DeleteTable() {
		setBounds(0, 0, 1200, 900);
//		setTitle("테이블 삭제");
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);

		pCenter = new Panel();
		pCenter.setBounds(0, 0, 1200, 830);
		this.add(pCenter);
		pCenter.setLayout(null);

		addTableButton();
		
		this.setVisible(true);

	}
	
	public void addTableButton(){
		pCenter.removeAll();
		tableButtons=new ArrayList<JButton>();
		tables = (ArrayList<TableLocation>) io.readDB("tableLocation");

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

			pCenter.add(tableButtons.get(i));
		}
		pCenter.repaint();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i=0;i<tables.size();i++){
			if(tables.get(i).getTableNo()==Integer.parseInt(e.getActionCommand())){
				io.removeObject("tableLocation", tables.get(i));					
//				tableButtons.remove(i);
			}
		}
		addTableButton();

	}
}
