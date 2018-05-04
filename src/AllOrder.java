import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;




public class AllOrder extends JFrame{
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
	public AllOrder(Staff st) throws SQLException
	{	this.st=st;
		  //items table
		{
Object[] columns2 = {"OrderId","Price"};
	        
	        model2.setColumnIdentifiers(columns2);
	       
	       // model = new DefaultTableModel();
	        // set the model to the table
	        table2.setModel(model2);
	       
	        // Change A JTable Background Color, Font Size, Font Color, Row Height
	        table2.setBackground(Color.LIGHT_GRAY);
	        table2.setForeground(Color.black);
	       
	        rs=con.GetOrders(st.username);
	        
	        while(rs.next())
	        {
	        	Object[] row = new Object[2];
				row[0]=rs.getString("orderId");
				row[1]=rs.getString("totalPrice");
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
		
	}
	
	public class allevent implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
			
			
			
		}
		
	}
	public class mouseevent implements MouseListener
	{
		public void mouseClicked(MouseEvent e){
			  // i = the index of the selected row
            int i = table.getSelectedRow();
            System.out.println("click");
            System.out.println(i);
           // ViewOrder vo = new ViewOrder(orderid);
            
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
             int orderid = Integer.parseInt( model2.getValueAt(i, 0).toString());
            ViewOrder vo;
			try {
				vo = new ViewOrder(st,orderid);
				 vo.setVisible(true);
		            vo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		            vo.setSize(600,600);
		            vo.setTitle("View Order");
		            vo.setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
