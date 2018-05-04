import javax.swing.*;



import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddItem extends JFrame {
	


	JLabel pricelabel;
	JLabel namelabel,existlabel,msglabel;
	JButton createbtn,returnbtn;
	JTextField namefield,pricefield;
	
 	public AddItem(){
 		setLayout(new FlowLayout());
 		namelabel=new JLabel("Item Name : ");
 		add(namelabel);
 		namefield=new JTextField(10);
 		add(namefield);
 		
 		pricelabel=new JLabel("Price : ");
 		add(pricelabel);
 		pricefield=new JTextField(10);
 		pricefield.setAlignmentX(Component.LEFT_ALIGNMENT);
 		add(pricefield);
 		
 		
 		
 		
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
			
			if(namefield.getText().isEmpty()||pricefield.getText().isEmpty())
			{  			msglabel.setText("Fill All Fields!");
			 }
			else{
				msglabel.setText("");
				res=db.ItemCheck(namefield.getText());
						if(res=="")
						{
							db.AddItem(namefield.getText(),Integer.parseInt(pricefield.getText()));
							existlabel.setText("Added!!");
						}
						else
						{
							existlabel.setText("Item Exists!");
							
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
