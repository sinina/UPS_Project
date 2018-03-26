package miniProject.run;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import miniProject.adapter.TenseUIAdapter;

public class Run {
	public Run() {

		JFrame frame = new JFrame();

		JButton backMain = new JButton("메인으로");
		 backMain.setBounds(1000,780,100,80);
		 backMain.setBackground(Color.gray);
		 backMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		 frame.add(backMain);
		frame.add(new TenseUIAdapter());
		frame.setSize(1200, 900);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		

		
	}

}
