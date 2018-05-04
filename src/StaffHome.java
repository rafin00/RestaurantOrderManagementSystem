import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;




public class StaffHome extends JFrame{
	private JLabel label;
	private JLabel usernamelabel,name,price,quantity,error;
	private JLabel passwordlabel;
	private ResultSet rs;
	private JButton loginbtn,addbtn,deletebtn,updatebtn,submitbtn,allorderbtn;
	private JButton signupbtn;
	private JTextField usernameField,nametext,pricetext,quantitytext;
	private JTextField passwordField;
	private JTable table2=new JTable();
	private JLabel inlabel;
	DBConnect con=new DBConnect();
	JTable table = new JTable(); 
	 DefaultTableModel model=new DefaultTableModel();
	 DefaultTableModel model2=new DefaultTableModel();
	
	Staff st = new Staff("");
	public StaffHome(Staff st) throws SQLException
	{	this.st=st;
		  //items table
		{
Object[] columns2 = {"Item Name","Price"};
	        
	        model2.setColumnIdentifiers(columns2);
	       
	       // model = new DefaultTableModel();
	        // set the model to the table
	        table2.setModel(model2);
	       
	        // Change A JTable Background Color, Font Size, Font Color, Row Height
	        table2.setBackground(Color.LIGHT_GRAY);
	        table2.setForeground(Color.black);
	       
	        rs=con.GetItems();
	        
	        while(rs.next())
	        {
	        	Object[] row = new Object[2];
				row[0]=rs.getString("itemName");
				row[1]=rs.getString("price");
				
				model2.addRow(row);
	        }
	        // create JTextFields
	         
	        
	       
	        
	       
	        
	      
	        
	        // create JScrollPane
	        JScrollPane pane2 = new JScrollPane(table2);
	        pane2.setBounds(0, 0, 300, 300);
	        
	        setLayout(null);
	        
	     add(pane2);
	        
	     mouseevent2 mu2 = new mouseevent2();
			table2.addMouseListener(mu2);
	    
	       
	        // create an array of objects to set the row data
		}
		//
	     //order table   
		{
	        // create a table model and set a Column Identifiers to this model 
	        Object[] columns = {"Item Name","quantity","Price"};
	        
	        model.setColumnIdentifiers(columns);
	       
	       // model = new DefaultTableModel();
	        // set the model to the table
	        table.setModel(model);
	       
	        // Change A JTable Background Color, Font Size, Font Color, Row Height
	        table.setBackground(Color.LIGHT_GRAY);
	        table.setForeground(Color.black);
	       
	        
	        // create JTextFields
	         nametext = new JTextField();
	         pricetext = new JTextField();
	         quantitytext = new JTextField();
	        error=new JLabel("");
	        // create JButtons
	      
	       deletebtn = new JButton("Delete");
	       updatebtn = new JButton("update");
	        submitbtn = new JButton("Submit");
	        allorderbtn=new JButton("All Orders");
	        nametext.setBounds(20, 420, 100, 25);
	        quantitytext.setBounds(20, 450, 100, 25);
	        pricetext.setBounds(20, 480, 100, 25);
	        pricetext.setEnabled(false);
	        updatebtn.setBounds(150, 420, 100, 25);
	       nametext.setEnabled(false);
	        deletebtn.setBounds(150, 450, 100, 25);
	        submitbtn.setBounds(150, 480, 100, 25);
	        allorderbtn.setBounds(150, 510, 100, 25);
	        error.setBounds(150, 470, 130, 160);
	        
	        add(allorderbtn);
	        // create JScrollPane
	        JScrollPane pane = new JScrollPane(table);
	        pane.setBounds(300, 0, 600, 300);
	        
	        setLayout(null);
	        add(error);
	     add(pane);
	        
	        // add JTextFields to the jframe
	        add(nametext);
	       add(pricetext);
	        add(quantitytext);
	        ;
	    
	        // add JButtons to the jframe
	       add(updatebtn);add(deletebtn);add(submitbtn);
	       event e = new event();
	       submitbtn.addActionListener(e);
	       
	        upevent eu = new upevent();
			updatebtn.addActionListener(eu);
	        delevent ed = new delevent();
			deletebtn.addActionListener(ed);
			mouseevent mu = new mouseevent();
			table.addMouseListener(mu);
			
			allevent ar = new allevent();
			allorderbtn.addActionListener(ar);
	        // create an array of objects to set the row data
	}//ordertable end
	}
	
	public class allevent implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			
			AllOrder ao;
			try {
				ao = new AllOrder(st);
				ao.setVisible(true);
				 ao.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				 ao.setSize(600,600);
				 ao.setTitle("All Orders");
				 ao.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		
	}
	public class mouseevent implements MouseListener
	{
		public void mouseClicked(MouseEvent e){
			  // i = the index of the selected row
            int i = table.getSelectedRow();
            System.out.println("click");
            System.out.println(i);
            nametext.setText(model.getValueAt(i, 0).toString());
            quantitytext.setText(model.getValueAt(i, 1).toString());
            pricetext.setText(model.getValueAt(i, 2).toString());
            
		}

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	public class mouseevent2 implements MouseListener
	{
		public void mouseClicked(MouseEvent e){
			  // i = the index of the selected row
            int i = table2.getSelectedRow();
            if(i>=0){
            System.out.println("click2");
            System.out.println(i);
            Object[] row = new Object[3];
            row[0]=model2.getValueAt(i, 0).toString();
            row[1]="1";
            row[2]=model2.getValueAt(i, 1).toString();
            int rows=table.getRowCount();
            int ex=1,ind=-1;
            for(int j=0;j<rows;j++)
            {
            	if(model.getValueAt(j, 0).toString().equals(row[0]))
            	{
            		ex=2;
            		String q =model.getValueAt(j, 1).toString();
            		int qi =Integer.parseInt(q);
            		qi=qi+1;
            		q=Integer.toString(qi);
            		row[1]= q;
            		ind=j;
            		model.setValueAt(row[1], j, 1);
            		System.out.println(row[1]+"se");
            		break;
            		
            	}
            }
            if(ex==1){
            System.out.println(row[0]);
    		System.out.println(row[1]);
    		System.out.println(row[2]);
    		model.addRow(row);
            }
            else
            {
            	
            }
		}
            
		}

		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	public class event implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			
			int rows=table.getRowCount();
			Object[][] orders=new Object[rows][3];
			int ex=1,total=0;
            if(rows>0){
            	for(int j=0;j<rows;j++)
            	{
            		total=total + Integer.parseInt(model.getValueAt(j, 1).toString())*Integer.parseInt(model.getValueAt(j, 2).toString());
            		orders[j][0]=model.getValueAt(j, 0).toString();
            		orders[j][1]=model.getValueAt(j, 1).toString();
            		orders[j][2]=model.getValueAt(j, 2).toString();
            		
            	}
            	con.GenOrder(total,st.username);
            	for(int j=0;j<rows;j++){
            	con.AddSubOrder(orders[j][0].toString(),Integer.parseInt(orders[j][1].toString()),Integer.parseInt(orders[j][2].toString()));
            	}
            	error.setText("Successful!");
            	model.setRowCount(0);
            }
            else
            {
            	error.setText("Order Cannot be empty");
            }
		}
		
	}
	public class upevent implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			 // i = the index of the selected row
            int i = table.getSelectedRow();
            
            if(i >= 0) 
            {
               model.setValueAt(nametext.getText(), i, 0);
               model.setValueAt(quantitytext.getText(), i, 1);
               model.setValueAt(pricetext.getText(), i, 2);
               }
            else{
                System.out.println("Update Error");
            }
		}
		
	}
	public class delevent implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			 // i = the index of the selected row
            int i = table.getSelectedRow();
           if(i>=0){
                model.removeRow(i);
           }
           else{
        	   System.out.println("dekete Error");
           }
		}
            
		
		
	}
	
	
}
