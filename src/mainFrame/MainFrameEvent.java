package mainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class MainFrameEvent implements ActionListener{
	JButton salesManagementBtn;
	JButton openStoreBtn;
	JButton storeManagementBtn;
	JButton exitBtn;	
	
	
	public MainFrameEvent(){}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==salesManagementBtn){
			System.out.println("확인");
		}
		if(e.getSource()==openStoreBtn){
			System.out.println("확인");
		}
		if(e.getSource()==storeManagementBtn){
			System.out.println("확인");
		}
		if(e.getSource()==exitBtn){
			System.out.println("확인");
		}
	}
}
