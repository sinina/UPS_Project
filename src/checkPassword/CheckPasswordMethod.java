package checkPassword;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fileIO.FileIO;
import miniProject.db.DaySales;

public class CheckPasswordMethod {
		private String adminId = "khadmin";
		private String password = "";
		private String masterpw="1q2w3e4r";
		JButton loginButton;
		JPasswordField pwF;
		JTextField idTF;
		JTextField startInput;
		JFrame frame;
		JButton changePwd;
		JPasswordField pwF2;
		DaySales ds;
		private String inputPassword;
		public boolean login;
		private int startMoney;
		private String makePassword="";
		
		FileIO io = new FileIO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		public void setLogin(boolean login) {
			this.login = login;
		}
		public boolean getLogin(){
			return login;
		}

		public CheckPasswordMethod(){
		}
		
		public void ChekingMethod(){
			frame = new JFrame("로그인창");
			frame.setBounds(500,300,300,300);
			frame.setLayout(null);
			
			
			JLabel idLabel = new JLabel("ID");
			idLabel.setLocation(50,50);
			idLabel.setSize(100,50);
			idLabel.setFont(new Font("돋움", Font.BOLD, 12));
			frame.add(idLabel);
			
			idTF = new JTextField(30);
			idTF.setBounds(160,50,100,50);
			frame.add(idTF);
			
			JLabel pwLabel = new JLabel("Password");
			pwLabel.setLocation(50,110);
			pwLabel.setSize(100,50);
			pwLabel.setFont(new Font("돋움", Font.BOLD, 12));
			frame.add(pwLabel);
			
			pwF = new JPasswordField(30);
			pwF.setBounds(160,120,100,50);
			frame.add(pwF);
			
			loginButton = new JButton("로그인");
			loginButton.setBounds(150, 180, 120, 50);
			loginButton.setFont(new Font("돋움", Font.BOLD, 12));
			loginButton.setForeground(Color.DARK_GRAY);
			loginButton.setBackground(Color.WHITE);
			
			frame.add(loginButton);
			loginButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					loginCheck();
					if(login){
						startPop();
					}
				}
			});
			
			changePwd = new JButton("비밀번호 교체");
			changePwd.setBounds(10, 180, 120, 50);
			changePwd.setFont(new Font("돋움", Font.BOLD, 12));
			changePwd.setForeground(Color.DARK_GRAY);
			changePwd.setBackground(Color.WHITE);
			frame.add(changePwd);
			changePwd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					loginCheck();
					if(login){
						changePassword();
					}
				}
			});
			
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		}
		
		public void loginCheck(){
			try(FileInputStream fs = new FileInputStream("password.txt");
					InputStreamReader isr = new InputStreamReader(fs);){
				char[] makingPassword = pwF.getPassword();
				for(int i=0;i<pwF.getPassword().length;i++){
					makePassword+=makingPassword[i];
				}
			if((pwF.getText().equals(masterpw)||pwF.getText().equals(makePassword))&&idTF.getText().equals(adminId)){
				JOptionPane.showMessageDialog(frame, "성공");
				login = true;
				frame.setVisible(false);
			}
			else{
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인하세요");
				login = false;
				idTF.setText("");
				pwF.setText("");
			}
			}catch(FileNotFoundException fnfe){
				fnfe.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void changePassword(){
			JFrame frame = new JFrame("비밀번호 교체");
			
			frame.setBounds(500,300,300,300);
			frame.setLayout(null);
			JLabel pwLabel = new JLabel("PassWord");
			pwLabel.setBounds(50,50,100,50);
			frame.add(pwLabel);
			
			pwF2 = new JPasswordField(30);
			pwF2.setBounds(160, 50, 100, 50);
			frame.add(pwF2);
		
			JButton changeButton = new JButton("바꾸기");
			changeButton.setBounds(100, 180, 100, 50);
			changeButton.setFont(new Font("돋움", Font.BOLD, 12));
			changeButton.setForeground(Color.DARK_GRAY);
			changeButton.setBackground(Color.WHITE);
			frame.add(changeButton);
			changeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try(FileOutputStream fos=new FileOutputStream("password.txt");
							OutputStreamWriter osw=new OutputStreamWriter(fos);) {
						char[] partPassword = pwF2.getPassword();
						for(int i = 0;i<pwF2.getPassword().length;i++){
							password+=partPassword[i];
						}
						osw.write(password);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(frame, "성공");
					frame.dispose();
				}
			});
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		}

		public int startPop(){
			frame = new JFrame("유지비 입력");
			frame.setBounds(500,300,300,300);
			frame.setLayout(null);
			
			JLabel startLabel = new JLabel("유지비 입력");
			startLabel.setLocation(50,50);
			startLabel.setSize(100,50);
			frame.add(startLabel);
			
			startInput = new JTextField(30);
			startInput.setBounds(160,50,100,50);
			
			JButton inputButton = new JButton("입력");
			inputButton.setBounds(100, 180, 100, 50);
			inputButton.setFont(new Font("돋움", Font.BOLD, 12));
			inputButton.setForeground(Color.DARK_GRAY);
			inputButton.setBackground(Color.WHITE);
			
			inputButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					startMoney = Integer.parseInt(startInput.getText());
					ArrayList<DaySales> daySales=io.readDB("DaySales");
					int i;
					for( i=0;i<daySales.size();i++){
						if(daySales.get(i).getDate().equals(sdf.format(new Date()))){
							daySales.get(i).setExpenses(startMoney);
							break;
						}
					}
					io.writeDB("DaySales", daySales);
					frame.dispose();
				}
			});
			frame.add(inputButton);
			frame.add(startInput);
			frame.setVisible(true);
			return startMoney;
		}
		
		
		
		
		public int getStartMoney() {
			return startMoney;
		}
		public void setStartMoney(int startMoney) {
			this.startMoney = startMoney;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
}
