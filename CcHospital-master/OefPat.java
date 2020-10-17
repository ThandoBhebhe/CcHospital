
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class OefPat extends JFrame {
	
	JTextField idjtf,fnjtf,addjtf,lnjtf,pnjtf,nojtf,medjtf;
	JTextArea crjta =null;
	//String callsMade =null;
	Object[][] data = new Object[100][4];
	boolean saved = true;
	
	
	Connection conn = null;
	Statement s = null;
	ResultSet rs = null;
	ResultSet rs1= null;
	
	

	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	
	 
	
	 
	public OefPat(){
		
		
		super("PATIENT");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		
		JMenuBar jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		JMenu options= new JMenu("OPTIONS");
		jmb.add(options);
		JMenuItem logout = new JMenuItem("LOGOUT");
		options.add(logout);
		//logoutOption		
		logout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				cc();
				
			}
			
		});
		
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
		
	
		
	     setSize(690,519);
	     this.setLocationRelativeTo(null);
	     setVisible(true);
	     
	 	 this.setLayout(new BorderLayout());
	 
			
			
    	  
    	    
	    	try {
	    	    conn =
	    	       DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
	    	  
	    	   

	    	    
	    	    s = conn.createStatement();

	    	
	    	    
	    	    if (s.execute("select * from medication;")) {
	    	        rs = s.getResultSet();
	    	    }

	    	 
	    	    

	    	    
	    	    int rc = 0;
	    	    
	    	    while(rs.next()){
	    	    
	    	    	String id = rs.getString("id");
					data[rc][0]=id;
					
					String category = rs.getString("category");
					data[rc][1]= category;
					
					String name = rs.getString("name");
					data[rc][2]= name;
					
					String price = rs.getString("price");
					data[rc][3]= price;
					 
					
					
	    	         rc++;    
	    	        
	        	    
	    	        
	    	    } 
	    	   
	    	} catch (SQLException ex) {
	    	    // handle any errors
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	    System.out.println("SQLState: " + ex.getSQLState());
	    	    System.out.println("VendorError: " + ex.getErrorCode());
	    	}
	    	
	    	
	    	String [] cn = {"ID","Category","Name","Price" };
	    	JTable table = new JTable(data, cn);
	    	
	    	JScrollPane js=new JScrollPane(table);
	    	
	    	
	         left.add(js);
	         
	    
	         
	         
	         this.add(bottom, BorderLayout.SOUTH);
	         
	         this.add(left, BorderLayout.WEST);
	       
	         
	         JPanel jp = new JPanel();
	 		jp.setBorder(BorderFactory.createLineBorder(Color.black,2));
	 		this.add(jp);
	 		

	 		JLabel idjl = new JLabel(" YOUR ID:          ");
	 		jp.add(idjl);
	 		idjtf = new JTextField(20);
	 		jp.add(idjtf);
	 		
	 		

	 		
	 		
	 		JLabel pnjl= new JLabel("Phone Number: ");
	 		jp.add(pnjl);
	 		pnjtf = new JTextField(20);
	 		jp.add(pnjtf);
	 		
	 		JLabel alert = new JLabel("NB: in oder To make a call  ");
	 		JLabel alert1 = new JLabel("please enter all fields above.  ");
	 		
	 		
	 		jp.add(alert);
	 		jp.add(alert1);
	 		
	 		JLabel space = new JLabel("                                                           ");
			jp.add(space);
	 		
	 		
	 		JLabel crjl = new JLabel("MAKE CALL ");
	 		jp.add(crjl);
	 		crjta = new JTextArea(4,20);
	 		jp.add(crjta);
	 		//call button
			JButton savecall = new JButton("SAVE CALL");
			bottom.add(exit);
			savecall.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent args0){
					
					
					
					try {
						s=conn.createStatement();
						
						String input = crjta.getText();
						String userid = idjtf.getText();
				
						String phnum = pnjtf.getText();
								
						
					    s.executeUpdate("update patient set call_records = '"+input+"' where id = '"+userid+"' and phone = '"+phnum+"'");
					    
					    s = conn.createStatement();
					    saved =false;
					    //s.execute("select * from patient where id = '"+userid+"' and phone = '"+phnum+"'");
						   rs=s.executeQuery("select * from patient where id = '"+userid+"' and phone = '"+phnum+"'");
					  
					    if(rs !=null){
						   while(rs.next()){
							   JOptionPane.showMessageDialog(null, "RECEPTION HAS RECEIVED YOUR CALL","SUCCESS",JOptionPane.PLAIN_MESSAGE);
							   saved =true;
						   }
						
					   
					   } if(saved==false){
					  JOptionPane.showMessageDialog(null, "CALL NOT SAVED, CHECK DETAILS","FAILED",JOptionPane.ERROR_MESSAGE);
					   }
						  
					   
					} catch (SQLException e) {
						 JOptionPane.showMessageDialog(null, "COULD NOT SAVE CALL: "+e,"CALL FAILED",JOptionPane.ERROR_MESSAGE);
					
						e.printStackTrace();
					}
				
				}
				
			});
			jp.add(savecall);
	         
	     validate();
	     repaint();
	     
	}
	
	public void cc(){
		this.setVisible(false);
		Oefen eofen = new Oefen();
		
		}



	
	
}
