import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateItem extends JFrame {
	


	JLabel pricelabel;
	JLabel namelabel,existlabel,msglabel;
	JButton updatebtn,returnbtn,deletebtn,searchbtn;
	JTextField namefield,pricefield;
	
 	public UpdateItem(){
 		setLayout(new FlowLayout());
 		namelabel=new JLabel("Item Name : ");
 		add(namelabel);
 		namefield=new JTextField(10);
 		add(namefield);
 		searchbtn=new JButton("Search");
 		add(searchbtn);
 		pricelabel=new JLabel("Price : ");
 		add(pricelabel);
 		pricefield=new JTextField(10);
 		pricefield.setAlignmentX(Component.LEFT_ALIGNMENT);
 		add(pricefield);
 		
 		
 		
 		
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
 		searchevent se = new searchevent();
 		searchbtn.addActionListener(se);
 		returnevent re = new returnevent();
 		returnbtn.addActionListener(re);
 		deleteevent de = new deleteevent();
 		deletebtn.addActionListener(de);
 		updateevent ue = new updateevent();
 		updatebtn.addActionListener(ue);
 		
 	}
 	
 	public class updateevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			DBConnect db = new DBConnect();
			String res="";
			
			if(namefield.getText().isEmpty()||pricefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				msglabel.setText("");
				
							db.UpdateItem(namefield.getText(),Integer.parseInt(pricefield.getText()));
							existlabel.setText("Successful!!");
						
						
				}
			}
 	}
 	
 	public class searchevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			DBConnect db = new DBConnect();
			String res="";
			
			if(namefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				msglabel.setText("");
				
							int pr =db.SearchItem(namefield.getText());
							
							if(pr!=0)
							{
								pricefield.setText(Integer.toString(pr));
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
 	
 	
 	public class deleteevent implements ActionListener
 	{
 		public void actionPerformed(ActionEvent e)
 		{
 			DBConnect db = new DBConnect();
			String res="";
			
			if(namefield.getText().isEmpty()||pricefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				
				
							db.DeleteItem(namefield.getText());
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
