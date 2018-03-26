package graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import fileIO.FileIO;
import miniProject.db.TableLocation;

public class SetTable extends Panel implements MouseListener {
	// TableLocation t;
	ArrayList<TableLocation> tables = new ArrayList<TableLocation>();
	Panel pCenter;
	ArrayList<JButton> tableButtons = new ArrayList<JButton>();

	FileIO io = new FileIO();

	public SetTable() {
		setBounds(0, 0, 1200, 900);
		// setTitle("테이블 목록");
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);

		pCenter = new Panel();
		pCenter.setBounds(0, 0, 1200, 830);
		this.add(pCenter);
		pCenter.setLayout(null);
		pCenter.addMouseListener(this);

		addTableButton();

		this.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String tableName = String.valueOf(tables.size() + 1);
//		System.out.println(tableName);
		TableLocation t = new TableLocation(tables.size() + 1, e.getX() - 100, e.getY() - 75);
		int start = tables.size();
		int ifNum = 0;
		for (ifNum = 0; ifNum < tables.size(); ifNum++) {
			if (tables.get(ifNum).getTableNo() != (ifNum + 1)) {

				tableName = String.valueOf(ifNum + 1);
				
				t = new TableLocation(ifNum + 1, e.getX() - 100, e.getY() - 75);
				io.editDB("tableLocation", t);

				for (int j = ifNum; j < start; j++) {
					tables=(ArrayList<TableLocation>) io.readDB("tableLocation");
					t = tables.get(ifNum); 
					io.editDB("tableLocation", t);
					io.removeIndexObject("tableLocation", ifNum);
				}
				break;
			}
		}
		if (ifNum == start) {
			io.editDB("tableLocation", t);
		}

		addTableButton();

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

	public void addTableButton() {

		pCenter.removeAll();
		repaint();
		tableButtons = new ArrayList<JButton>();
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

			pCenter.add(tableButtons.get(i));

		}
	}
}
