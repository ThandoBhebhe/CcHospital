

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class printout extends JFrame {
	
	JTextField idjtf,fnjtf,addjtf,lnjtf,pnjtf,nojtf,medjtf;
	JTextArea crjta =null;
	//String callsMade =null;
	Object[][] data = new Object[100][9];
	boolean saved = true;
	
	
	Connection conn = null;
	Statement s = null;
	ResultSet rs = null;
	ResultSet rs1= null;
	

	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	
	
	public printout(){
		
		
		super("ALL BILLS");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		
		//exit button
		JButton exit = new JButton("EXIT");
		bottom.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				
				int choice = JOptionPane.showConfirmDialog(null, 
						   "Are you sure you wish to exit application?","FINAL CHECK", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION) {
					    System.exit(0);
					}
				
			}
			
		});
		
	
		
	     setSize(500,519);
	     this.setLocationRelativeTo(null);
	     setVisible(true);
	     
	 	 this.setLayout(new BorderLayout());
	 
    	    
	    	try {
	    	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
	    	 
	    	    s = conn.createStatement();
	    	    
	    	    if (s.execute("select * from patient;")) {
	    	        rs = s.getResultSet();
	    	    }
	    	    
	    	    int rc = 0;
	    	    
	    	    while(rs.next()){
	    	    
	    	    	String id = rs.getString("id");
					data[rc][0]=id;
					
					String firstname = rs.getString("firstname");
					data[rc][1]= firstname;
					
					String lastname = rs.getString("lastname");
					data[rc][2]= lastname;
					
					String dob = rs.getString("dob");
					data[rc][3]= dob;
					
					String address = rs.getString("address");
					data[rc][4]= address;
					
					String phone = rs.getString("phone");
					data[rc][5]= phone;
					
					String meds = rs.getString("meds");
					data[rc][6]= meds;
					
					String price = rs.getString("price");
					data[rc][7]= price;
					 
					
	    	         rc++;    
	    	       
	    	    } 
	    	   
	    	} catch (SQLException ex) {
	    	    
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	    System.out.println("SQLState: " + ex.getSQLState());
	    	    System.out.println("VendorError: " + ex.getErrorCode());
	    	}
	    	
	    	
	    	String [] cn = {"ID","Firtname","Lastname","DOB","Address","PhoneNum","Meds","Price"};
	    	JTable table = new JTable(data, cn);
	    	
	    	JScrollPane js=new JScrollPane(table);
	    	
	    	
	         left.add(js);
	         
	         
	         this.add(bottom, BorderLayout.SOUTH);
	         
	         this.add(left, BorderLayout.WEST);
	       
	         
	     validate();
	     repaint();
	     
	}
	
	
}
