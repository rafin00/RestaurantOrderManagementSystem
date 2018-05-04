import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateStaff extends JFrame {
	


	JLabel pricelabel;
	JLabel usernamelabel,fullnamelabel,passwordlabel,existlabel,msglabel;
	JButton updatebtn,returnbtn,deletebtn,searchbtn;
	JTextField usernamefield,fullnamefield,passwordfield;
	
 	public UpdateStaff(){
 		setLayout(new FlowLayout());
 		usernamelabel=new JLabel("UserName : ");
 		add(usernamelabel);
 		usernamefield=new JTextField(10);
 		add(usernamefield);
 		
 		searchbtn=new JButton("Search");
 		add(searchbtn);
 		
 		fullnamelabel=new JLabel("Fullname : ");
 		add(fullnamelabel);
 		fullnamefield=new JTextField(10);
 		add(fullnamefield);
 		
 		
 		
 		
 		
 		updatebtn=new JButton("Update");
 		updatebtn.setEnabled(false);
 		add(updatebtn);
 		deletebtn=new JButton("Delete");
 		deletebtn.setEnabled(false);
 		add(deletebtn);
 		returnbtn=new JButton("Return");
 		add(returnbtn);
 		msglabel=new JLabel("");
 		add(msglabel);
 		existlabel=new JLabel("");
 		add(existlabel);
 		searchstaffevent se = new searchstaffevent();
 		searchbtn.addActionListener(se);
 		returnevent re = new returnevent();
 		returnbtn.addActionListener(re);
 		deletestaffevent de = new deletestaffevent();
 		deletebtn.addActionListener(de);
 		updatestaffevent ue = new updatestaffevent();
 		updatebtn.addActionListener(ue);
 		
 	}
 	
 	public class updatestaffevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			DBConnect db = new DBConnect();
			String res="";
			
			if(usernamefield.getText().isEmpty()||fullnamefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				msglabel.setText("");
				
							db.UpdateStaff(usernamefield.getText(),fullnamefield.getText());
							existlabel.setText("Successful!!");
						
						
				}
			}
 	}
 	
 	public class searchstaffevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			DBConnect db = new DBConnect();
			String res="";
			
			if(usernamefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				msglabel.setText("");
				
							String pr =db.SearchStaff(usernamefield.getText());
							
							if(pr!="")
							{
								fullnamefield.setText(pr);
								updatebtn.setEnabled(true);
								deletebtn.setEnabled(true);
							}
							else
							{
								existlabel.setText("Not Found!");
							}
							}
				}
			}
 	
 	
 	
 	
 	public class deletestaffevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			DBConnect db = new DBConnect();
			String res="";
			
			if(usernamefield.getText().isEmpty()||fullnamefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				
				
							db.DeleteStaff(usernamefield.getText());
							existlabel.setText("Successful!!");
							updatebtn.setEnabled(false);
							deletebtn.setEnabled(false);
						
						
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
