import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;






public class ViewOrder extends JFrame{
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
	int orderid=-1;
	Staff st = new Staff("");
	public ViewOrder(Staff st,int orderid) throws SQLException
	{	this.st=st;
		this.orderid=orderid;
		  //items table
		{
Object[] columns2 = {"OrderId","Item Name","Quantity","Price"};
	        
	        model2.setColumnIdentifiers(columns2);
	       
	       // model = new DefaultTableModel();
	        // set the model to the table
	        table2.setModel(model2);
	       
	        // Change A JTable Background Color, Font Size, Font Color, Row Height
	        table2.setBackground(Color.LIGHT_GRAY);
	        table2.setForeground(Color.black);
	       
	        rs=con.GetSubOrder(orderid);
	        
	        while(rs.next())
	        {
	        	Object[] row = new Object[4];
				row[0]=rs.getString("orderId");
				row[1]=rs.getString("itemName");				
				row[2]=rs.getString("quantity");
				row[3]=rs.getString("price");
				model2.addRow(row);
				
	        }
	        // create JTextFields
	         
	        
	       
	        
	       
	        
	        deletebtn = new JButton("Submit");
			add(deletebtn);
	        
	        // create JScrollPane
	        JScrollPane pane2 = new JScrollPane(table2);
	        pane2.setBounds(0, 0, 300, 300);
	        deletebtn.setBounds(150, 450, 100, 25);
	        setLayout(null);
	        
	     add(pane2);
	        
	     mouseevent2 mu2 = new mouseevent2();
			table2.addMouseListener(mu2);
			
			 delevent ed = new delevent();
				deletebtn.addActionListener(ed);
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
          
           
                con.DeleteOrder(orderid);
           
           
		}
            
		
		
	}
	
	
}
