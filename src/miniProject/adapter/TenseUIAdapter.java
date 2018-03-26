package miniProject.adapter;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import miniProject.ui.Magam;
import miniProject.ui.TenseUI;
import miniProject.ui.Today;

public class TenseUIAdapter extends JPanel {

	private JTabbedPane tabbedPane = new JTabbedPane();

	private TenseUI tnesUI = new TenseUI();
	private Magam magamUI = new Magam();
	private Today todayUI = new Today();

	public TenseUIAdapter() {

		tabbedPane.setBackground(Color.getColor("#CCCCCC"));
		tabbedPane.add("시재", tnesUI);
		tabbedPane.addTab("마감", magamUI);
		tabbedPane.addTab("금일 순매출", todayUI);

		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				switch (tabbedPane.getSelectedIndex()) {
				case 0:
					break;
				case 1:
					magamUI.setJtf(tnesUI.getjtextField());
					magamUI.test(tabbedPane);
					break;
				case 2:

					todayUI.getToday();
					break;
				}

			}
		});

		this.setLayout(null);
		this.add(tabbedPane).setBounds(0, 0, 1170, 860);
	}

}
