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
import javax.swing.BoxLayout;
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


public class OefRec extends JFrame {
	
	JTextField idjtf,fnjtf,addjtf,lnjtf,pnjtf,dobjtf,medjtf,crjtf,djtf,tjtf;
	//String callsMade =null;
	JTable table = null;
	JTextArea nojta,pnjta;
	//String username;
	Object[][] data = new Object[100][9];
	
	Connection conn = null;
	Statement s = null;
	ResultSet rs = null;
	ResultSet rs1= null;
	String notes;
	String TID;
	
	 

	//JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel left = new JPanel();
	//JPanel right = new JPanel();
	
	public void cc(){
		this.setVisible(false);
		Oefen eofen = new Oefen();
		eofen.setVisible(true);
		}
	
	
	public OefRec() throws ParseException {
		
		
		super("RECEPTION");
		
		
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
		
		
		
	
		
	
	     this.setSize(860,684);
	     this.setLocationRelativeTo(null);
	     setVisible(true);
	     
	 	 this.setLayout(new BorderLayout());
	 
			
			
    	  
    	    
	    	try {
	    	    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
	    	   

	    	    
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
				        //java.sql.Date sql = new java.sql.Date(parsed.getTime());
					
					//String dob = rs.getString("dob");
					data[count][3]= parsed;
					
					String address = rs.getString("address");
					data[count][4]= address;
					
					String phone = rs.getString("phone");
					data[count][5]= phone;
					
					String meds = rs.getString("meds");
					data[count][6]= meds;
					
					String cr = rs.getString("call_records");
					data[count][7]= cr;
					
					notes = rs.getString("notes");
					data[count][8]= notes;
					
	    	         count++;    
	    	        
	        	    
	    	        
	    	    } 
	    	   
	    	} catch (SQLException ex) {
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	 
	    	}
	    	
	    	
	    	String [] cn = {"ID","Firtname","Lastname","DOB","Address","PhoneNum","Meds","Call Records","notes"};
	    	
	    	
	    	//////////////////////////////////////////////////////
	    	table = new JTable(data, cn);
	    	
	    	JScrollPane js=new JScrollPane(table);
	    	
	        table.addMouseListener(new MouseAdapter(){
		    		public void mouseClicked(MouseEvent mc){
		    			
		    			int row = table.getSelectedRow();
		    			//String TID =(table.getModel().getValueAt(row, 0).toString());
		    			
		    			
		    			String TDN = table.getModel().getValueAt(row,7).toString();
		    			pnjta.setText(TDN);
		    			
		    			try{
		    				//System.out.println(TID);
		    		
		    				
		    				while(rs.next()){
		    					pnjta.setText(rs.getString(7));
		    				//	System.out.println(rs.getString("call_records"));
		    					
		  
		    				}
		    				
		    			}catch(Exception tab){
		    				
		    			}
		    		}
		    		
		    	});
	    	
	    	
	         left.add(js);
	         
	    
	         
	         
	         this.add(bottom, BorderLayout.PAGE_END);
	         
	         this.add(left, BorderLayout.LINE_START);
	       
	         
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
	 		
	 		JLabel dobjl= new JLabel("Date Of Birth: ");
	 		JLabel note = new JLabel("  Format YYYYMMDD       ");
	 		
	 		jp.add(dobjl);
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
	
	 		
	 		
	 		//call button
			JButton savecall = new JButton("ADD PATIENT");
			
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
			
			bottom.add(exit);
			
			savecall.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent args0){
					
					
					try {
						s=conn.createStatement();
						
				
						String firstname = fnjtf.getText();
						String lastname = lnjtf.getText();
						String dob = dobjtf.getText();
						String address = addjtf.getText();
						String phone = pnjtf.getText();
					
					
						if(firstname.equals("")|| lastname.equals("") || dob.equals("")|| address.equals("") || phone.equals("")){
							JOptionPane.showMessageDialog(null, "ENTER ALL FIELDS BEFORE ADDING","INSERT VALUES",JOptionPane.ERROR_MESSAGE);
						}else{
							s.executeUpdate("INSERT INTO `patient` (`firstname`, `lastname`, `dob`, `address`, `phone`) VALUES ('"+firstname+"', '"+lastname+"', '"+dob+"', '"+address+"', '"+phone+"')");
					    JOptionPane.showMessageDialog(null, "NEW PATIENT ADDED","SUCCESS",JOptionPane.PLAIN_MESSAGE);
						}
						
					    
						
					} catch (SQLException e) {
						 JOptionPane.showMessageDialog(null, "COULD NOT ADD PATIENT: "+e,"SUCCESS",JOptionPane.ERROR_MESSAGE);
					
						e.printStackTrace();
					}
				
				}
				
			});
			jp.add(savecall);
			
			JLabel patientnotes = new JLabel( "           PATIENT NOTES             ");
			jp.add(patientnotes);
	        pnjta = new JTextArea(4,20);
	        jp.add(pnjta);
			
			JLabel sendnotes = new JLabel( "           CREATE NOTES             ");
			JLabel spalert = new JLabel( "NB: SELECT PAITENT ON THE");
			JLabel spalert1 = new JLabel(" TABLE AND THEIR ID WILL");
			JLabel spalert2 = new JLabel(" BE USED TO SEND THE NOTES");
			JLabel spalert3 = new JLabel("  OR MAKE AN APPOINTMENT ");

			jp.add(spalert);
			jp.add(spalert1);
			jp.add(spalert2);
			jp.add(spalert3);
			JLabel space = new JLabel("                           ");
			jp.add(space);
			

			jp.add(sendnotes);
	        JTextArea snjta = new JTextArea(4,20);
	        jp.add(snjta);
	        
	        JButton snjb = new JButton("SEND NOTES");
	        jp.add(snjb);
	        snjb.addActionListener(new ActionListener(){
	        	
	    		public void actionPerformed(ActionEvent ae){
	    			
	 
	    			
	    			try{
	    				int row = table.getSelectedRow();
		    			  TID =(table.getModel().getValueAt(row, 0).toString());
		    			String username = table.getModel().getValueAt(row,2).toString();
		    			
	    				String gotten = snjta.getText();
	    				if(gotten.equals("")){
	    					JOptionPane.showMessageDialog(null, "NOTES NOT SENT, CHECK VALUES","FAILED",JOptionPane.ERROR_MESSAGE);
	    				}else{
	    			    s.executeUpdate("update patient set notes ='"+gotten+"' where id ='"+TID+"'");
    					JOptionPane.showMessageDialog(null, "DOCTOR HAS RECEIVED NOTES REGARDING "+username.toUpperCase() ,"SUCCESS",JOptionPane.PLAIN_MESSAGE);

	    				
	    				}
	    				
	    			
	    				
	    			}catch(Exception tab){
	    				
	    			}
	    		}
	    		
	    	});
	        
	        JPanel rjp = new JPanel();
	        
	        JLabel makeApp=new JLabel("            MAKE APPOINTMENT");
	        rjp.add(makeApp);
	        
	        
	        
	        JLabel rnj2=new JLabel("                        DATE:");

	        rjp.add(rnj2);
	      
	        
	        djtf = new JTextField(10);
	        rjp.add(djtf);
	        JLabel blanks=new JLabel(" ");
	        rjp.add(blanks);
	        JLabel blanks1=new JLabel(" ");
	        rjp.add(blanks1);
	        
	        
	        JLabel rnj0=new JLabel("                        TIME:");
	        rjp.add(rnj0);
	        
	        tjtf = new JTextField(10);
	        rjp.add(tjtf);
	        
	        JButton makeAppointmentBut= new JButton("   MAKE APPOINTMENT ");
	        makeAppointmentBut.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent ev){
	        		
	        		
	        		try {
	        		
	        			 
						String gottenDate=djtf.getText();
	        		    String gottenTime=tjtf.getText();
	        		    
	        		    int row = table.getSelectedRow();
		    			  TID =(table.getModel().getValueAt(row, 0).toString());
		    			String username = table.getModel().getValueAt(row,2).toString();	
		    			
	        		    
						s.execute("UPDATE `hospital`.`patient` SET `date`='"+gottenDate+"', `time`='"+gottenTime+"' WHERE  `id`='"+TID+"'");
						//"update patient set date =,time= where id = "
						JOptionPane.showMessageDialog(null, "APPOINTMENT BOOKED FOR "+username.toUpperCase(),"BOOKED",JOptionPane.PLAIN_MESSAGE);
						//+username.toUpperCase()
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        });
	        rjp.add(makeAppointmentBut);
	        

	        //Just adding some blank JLabels as i could not get some elements to be where they should
	        // without this my boxes looked huge, this helped me shrink them down to size
	        for(int i=0;i<35;i++){

	        	JLabel blankspace=new JLabel(" ");
	            rjp.add(blankspace);
	        	
	        }
	       
	        
	        
	        
	         this.add(rjp,BorderLayout.LINE_END);
	        rjp.setLayout(new BoxLayout(rjp,BoxLayout.Y_AXIS));
	       

	     validate();
	     repaint();
	     
	}
	
//




	
	
}
