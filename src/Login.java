import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;


public class Login extends JFrame{
	private JLabel label;
	private JLabel usernamelabel;
	private JLabel passwordlabel;
	private ResultSet rs;
	private JButton loginbtn;
	private JButton signupbtn;
	private JTextField usernameField;
	private JTextField passwordField;
	
	private JLabel inlabel;
	
	
	
	public Login()
	{
		setLayout(new FlowLayout());
		label = new JLabel("Login");
		add(label);
		
		usernamelabel = new JLabel("username");
		add(usernamelabel);
		usernameField = new JTextField("rafin",10);
		add(usernameField);
	passwordlabel = new JLabel("Password");
		add(passwordlabel);
		
		passwordField = new JTextField("rafin",10);
		
		add(passwordField);
		loginbtn = new JButton("Submit");
		add(loginbtn);
		
		
		inlabel = new JLabel("");
		add(inlabel);
		
		event e = new event();
		loginbtn.addActionListener(e);
	}
	public class event implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			DBConnect connect = new DBConnect();
			String res;
			
				res = connect.Login(usernameField.getText(),passwordField.getText());
				if(res=="")
					inlabel.setText("Invalid");
				else
				{
					if(res.equals("Staff")){
						Staff st = new Staff(usernameField.getText());
						
						
						StaffHome sh;
						try {
							sh = new StaffHome(st);
							sh.setVisible(true);
							 sh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							 sh.setSize(1000,600);
							 sh.setTitle("Staff Panel");
							 sh.setVisible(true);
							dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					else {
						AdminPanel ap = new AdminPanel();
						ap.setVisible(true);
						 ap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						 ap.setSize(600,600);
						 ap.setTitle("Admin Panel");
						 ap.setVisible(true);
						dispose();
					}
				}
				
		}
	}
	
	
	
	
}
