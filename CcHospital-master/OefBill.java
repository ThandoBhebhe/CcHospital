import java.text.ParseException;
import java.util.Date;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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
import javax.swing.JTextField;
import javax.swing.UIManager;

public class OefBill extends JFrame {
	
	
	JTextField idjtf,fnjtf,addjtf,lnjtf,pnjtf,dobjtf,medjtf,crjtf,pjtf;
	
	//String callsMade =null;
	Object[][] data = new Object[100][8];
	
	
	Connection conn = null;
	Statement s = null;
	ResultSet rs = null;
	ResultSet rs1= null;
	
	 

	JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel left = new JPanel();
	JPanel right = new JPanel();
	
	
	public OefBill() throws ParseException {
		
		
		super("BILLER");
		
	
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
	    	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3309/hospital","root","");
	    	   

	    	    
	    	    s = conn.createStatement();

	    	
	    	    
	    	    if (s.execute("select * from patient;")) {
	    	        rs = s.getResultSet();
	    	    }

	    	 
	    	    

	    	    
	    	    int count = 0;
	    	    
	    	    while(rs.next()){
	    	    
	    	    	String id = rs.getString("id");
					data[count][0]=id;
					
					String firstname = rs.getString("firstname");
					data[count][1]= firstname;
					
					String lastname = rs.getString("lastname");
					data[count][2]= lastname;
					
					  SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					  
				        Date parsed = format.parse(rs.getString(4));
				        java.sql.Date sql = new java.sql.Date(parsed.getTime());
					
					//String dob = rs.getString("dob");
					data[count][3]= parsed;
					
					String address = rs.getString("address");
					data[count][4]= address;
					
					String phone = rs.getString("phone");
					data[count][5]= phone;
					
					String meds = rs.getString("meds");
					data[count][6]= meds;
					
					String price = rs.getString("price");
					data[count][7]= price;
			
	    	    	
	    	         count++;    
	    	        
	        	    
	    	        
	    	    } 
	    	   
	    	} catch (SQLException ex) {
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	 
	    	}
	    	
	    	
	    	String [] cn = {"ID","Firtname","Lastname","DOB","Address","PhoneNum","Meds","Price"};
	    	
	    	
	    	JTable table = new JTable(data, cn);

	      	table.addMouseListener(new MouseAdapter(){
	    		public void mouseClicked(MouseEvent mc){
	    			
	    			int row = table.getSelectedRow();
	    			String TID =(table.getModel().getValueAt(row, 0).toString());
	    			
	    			
	    			try{
	    				
	    				s = conn.createStatement();
	    				rs = s.executeQuery("select * from patient where id='"+TID+"'");
	    				
	    				while(rs.next()){
	    					idjtf.setText(rs.getString(1));
	    					fnjtf.setText(rs.getString(2));
	    					lnjtf.setText(rs.getString(3));;
	    					dobjtf.setText(rs.getString(4));;
	    					addjtf.setText(rs.getString(5));;
	    					pnjtf.setText(rs.getString(6));
	    					
	    					
	    					
	    					
	    					medjtf.setText(rs.getString(7));;
	    					pjtf.setText(rs.getString(9));;
	    					
	  
	    				}
	    				
	    			}catch(Exception tab){
	    				
	    			}
	    		}
	    		
	    	});
	    	
	    	//////////////////////////////
	    	
	    	JScrollPane js=new JScrollPane(table);
	        left.add(js);
	    	
	 
	         
	    
	         
	         
	         this.add(bottom, BorderLayout.SOUTH);
	         
	         this.add(left, BorderLayout.WEST);
	       
	         
	         JPanel jp = new JPanel();
	 		jp.setBorder(BorderFactory.createLineBorder(Color.black));
	 		this.add(jp);
	 		

	 		
	 		JLabel fnjl = new JLabel("Firstname: ");
	 		jp.add(fnjl);
	 		fnjtf = new JTextField(20);
	 		jp.add(fnjtf);
	 		
	 		JLabel lnjl = new JLabel("Lastname: ");
	 		jp.add(lnjl);
	 		lnjtf = new JTextField(20);
	 		jp.add(lnjtf);
	 		
	 		JLabel dobl= new JLabel("Date Of Birth: ");
	 		JLabel note = new JLabel("Format YYYYMMDD");
	 		
	 		jp.add(dobl);
	 		jp.add(note);
	 		dobjtf = new JTextField(20);
	 		jp.add(dobjtf);
	 		
	 		JLabel addjl = new JLabel("Address: ");
	 		jp.add(addjl);
	 		addjtf = new JTextField(20);
	 		jp.add(addjtf);
	 		
	 		JLabel pnjtl = new JLabel("Phone: ");
	 		jp.add(pnjtl);
	 		pnjtf = new JTextField(20);
	 		jp.add(pnjtf);
	
	 		JButton printb = new JButton("PRINT BILLS");
	 		bottom.add(printb);
	 		printb.addActionListener(new ActionListener(){
	 			public void actionPerformed(ActionEvent evs){
	 				printout printout = new printout();
	 				
	 			}
	 		});
			
			
			
			
	         
	     validate();
	     repaint();
	     
	}
	
	public void cc(){
		this.setVisible(false);
		Oefen eofen = new Oefen();
		eofen.setVisible(true);
		}


	
	
}
