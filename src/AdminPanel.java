import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class AdminPanel extends JFrame {

	private JButton newStaffbtn;
	private JButton updateStaffbtn;
	private JButton newItembtn;
	private JButton updateItembtn;
	
	public AdminPanel(){
	
		setLayout(new FlowLayout());
		newStaffbtn= new JButton("Add New Staff");
		add(newStaffbtn);
		updateStaffbtn= new JButton("Update/Delete Staff");
		add(updateStaffbtn);
		newItembtn= new JButton("Add New Item");
		add(newItembtn);
		updateItembtn= new JButton("Update/Delete Item");
		add(updateItembtn);
		
		eventNU e1 = new eventNU();
		newStaffbtn.addActionListener(e1);
		eventANU e2 = new eventANU();
		updateStaffbtn.addActionListener(e2);
		eventAI e3 = new eventAI();
		newItembtn.addActionListener(e3);
		eventANI e4 = new eventANI();
		updateItembtn.addActionListener(e4);
		
	}
	
	public class eventNU implements ActionListener
	{
		public void actionPerformed(ActionEvent e1){
			AddStaff as = new AddStaff();
			as.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 as.setSize(600,600);
			 as.setTitle("New User");
			 as.setVisible(true);
			 dispose();
		}
	}
	public class eventANU implements ActionListener
	{
		public void actionPerformed(ActionEvent e2){
			
			UpdateStaff us = new UpdateStaff();
			us.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 us.setSize(600,600);
			 us.setTitle("Update/Delete User");
			 us.setVisible(true);
			 dispose();
		}
	}
	public class eventAI implements ActionListener
	{
		public void actionPerformed(ActionEvent e3){
			
			AddItem ai = new AddItem();
			ai.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 ai.setSize(600,600);
			 ai.setTitle("New Item");
			 ai.setVisible(true);
			 dispose();
		}
	}
	public class eventANI implements ActionListener
	{
		public void actionPerformed(ActionEvent e4){
			

			UpdateItem ani = new UpdateItem();
			ani.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 ani.setSize(600,600);
			 ani.setTitle("Update/Delete Item");
			 ani.setVisible(true);
			 dispose();
		}
	}

	
}
