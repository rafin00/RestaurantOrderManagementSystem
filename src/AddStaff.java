import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddStaff extends JFrame {
	

	JLabel usernamelabel;
	JLabel passwordlabel;
	JLabel fullnamelabel,msglabel,existlabel;
	JButton createbtn,returnbtn;
	JTextField usernamefield,passwordfield,fullnamefield;
	
 	public AddStaff(){
 		setLayout(new FlowLayout());
 		usernamelabel=new JLabel("Username : ");
 		usernamelabel.setAlignmentX(Component.LEFT_ALIGNMENT);
 		add(usernamelabel);
 		
 		usernamefield=new JTextField(10);
 		usernamefield.setAlignmentX(Component.LEFT_ALIGNMENT);
 		add(usernamefield);
 		
 		
 		passwordlabel=new JLabel("Password : ");
 		add(passwordlabel);
 		passwordfield=new JTextField(10);
 		add(passwordfield);
 		fullnamelabel=new JLabel("Fullname : ");
 		add(fullnamelabel);
 		fullnamefield=new JTextField(10);
 		add(fullnamefield);
 		createbtn=new JButton("Create");
 		add(createbtn);
 		returnbtn=new JButton("Return");
 		add(returnbtn);
 		msglabel=new JLabel("");
 		add(msglabel);
 		existlabel=new JLabel("");
 		add(existlabel);
 		createevent ce = new createevent();
 		createbtn.addActionListener(ce);
 		returnevent re = new returnevent();
 		returnbtn.addActionListener(re);
 	}
 	
 	public class createevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			DBConnect db = new DBConnect();
			String res="";
			
			if(usernamefield.getText().isEmpty()||passwordfield.getText().isEmpty()||fullnamefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				msglabel.setText("");
				res=db.UserCheck(usernamefield.getText());
						if(res=="")
						{
							db.AddStaff(usernamefield.getText(),passwordfield.getText(),fullnamefield.getText());
							existlabel.setText("Added!!");
						}
						else
						{
							existlabel.setText("user Exists");
							
						}
				}
			}
 	}
 	
 	public class returnevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			AdminPanel ap = new AdminPanel();
 			ap.setVisible(true);
			 ap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 ap.setSize(600,600);
			 ap.setTitle("adm");
			 ap.setVisible(true);
			dispose();
			}
 	}
 	
}
