
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


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.table.TableColumnModel;

public class OefDoc extends JFrame{
	
	JTextField idjtf,fnjtf,addjtf,lnjtf,pnjtf,nojtf,djtf,tjtf,medjtf,dobjtf,mjtf,catAamountjtf,catBamountjtf,catCamountjtf;
	JTextArea crjta =null,detjta;
	JComboBox catAmjcb;
	JComboBox catBmjcb;
	JComboBox catCmjcb;
	
	Object[][] data = new Object[100][9];
	boolean saved = true;
	
	
	Connection conn = null;
	Statement s = null;
	ResultSet rs = null;
	ResultSet rs1= null;
	
	int aprice,bprice,cprice;
	
	 
	//JPanel top = new JPanel();
	JPanel bottom = new JPanel();
	JPanel left = new JPanel();
	//JPanel right = new JPanel();
	
	
	public OefDoc(){
		
		
		super("DOCTOR");
		
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
		
	
	     this.setSize(1000,630);
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
	    	    //result set fetches data in the data underneath the rows specified
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
					
					String cr = rs.getString("call_records");
					data[rc][7]= cr;
					
					//incrementing row counter to go to the next line
	    	        rc++;    
	    	        
	        	    
	    	        
	    	    } 
	    	   
	    	} catch (SQLException ex) {
	    	    // handle any errors
	    	    System.out.println("SQLException: " + ex.getMessage());
	    	    System.out.println("SQLState: " + ex.getSQLState());
	    	    System.out.println("VendorError: " + ex.getErrorCode());
	    	}
	    	
	    	
	    	String [] cn = {"ID","Firstname","Lastname","DOB","Address","PhoneNum","Meds","Call Records"};
	    	
	    	///////////////////////////////////////////////////
	    	
	    	JTable table = new JTable(data, cn);
	    	TableColumnModel tcm = table.getColumnModel();
	    	
	    	tcm.getColumn(0).setPreferredWidth(50);
	    	System.out.println("Trying to set width");
	    	tcm.getColumn(1).setPreferredWidth(50);
	    	tcm.getColumn(2).setPreferredWidth(40);
	    	tcm.getColumn(3).setPreferredWidth(50);
	    	tcm.getColumn(4).setPreferredWidth(50);
	    	tcm.getColumn(5).setPreferredWidth(50);
	    	tcm.getColumn(6).setPreferredWidth(50);
	    	tcm.getColumn(7).setPreferredWidth(50);
	    	
	    	
	    	
	    	JScrollPane js=new JScrollPane(table);
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
	    					lnjtf.setText(rs.getString(3));
	    					dobjtf.setText(rs.getString(4));
	    					addjtf.setText(rs.getString(5));
	    					pnjtf.setText(rs.getString(6));
	    					
	    					medjtf.setText(rs.getString(7));
	    					crjta.setText(rs.getString(9));
	    					System.out.println("priting line");
	    				}
	    				
	    			}catch(Exception tab){
	    				System.out.println("Something went wrong with fetching the data from the database");
	    			}
	    		}
	    		
	    	});
	    	
	        left.add(js);
	         
	        this.add(bottom, BorderLayout.SOUTH);
	         
	        this.add(left, BorderLayout.WEST);
	       
	         
	        JPanel jp = new JPanel();
	 		jp.setBorder(BorderFactory.createLineBorder(Color.black));
	 		this.add(jp);
	 		
	 		JLabel idjtl = new JLabel("ID: ");
	 		jp.add(idjtl);
	 		idjtf = new JTextField(20);
	 		jp.add(idjtf);
	 		
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
	 		
			JLabel medjtl = new JLabel("Medicine: ");
	 		jp.add(medjtl);
	 		medjtf = new JTextField(20);
	 		jp.add(medjtf);
	 		
	 		
	 		JLabel crjl = new JLabel("NOTES ");
	 		jp.add(crjl);
	 		crjta = new JTextArea(4,21);
	 		jp.add(crjta);
	 		
	 		JPanel rjp = new JPanel();
	 		
	 		
	        
	        JLabel makeApp=new JLabel("                  MAKE NOTE");
	        rjp.add(makeApp);
	        
	        JLabel blanks1=new JLabel(" ");
	        rjp.add(blanks1);
	        
	        
	        
	      
	        
	        djtf = new JTextField(10);
	        rjp.add(djtf);
	        
	        
	        JLabel blanks=new JLabel(" ");
	        rjp.add(blanks);
	        
	        
	        JLabel rnj0=new JLabel("                   VISIT TIME:");
	        rjp.add(rnj0);
	        
	        tjtf = new JTextField(10);
	        rjp.add(tjtf);
	        
	        JLabel mjtl=new JLabel("             MIDICATION INFO:");
	        rjp.add(mjtl);
	        
	        mjtf = new JTextField(10);
	        rjp.add(mjtf);
	        JLabel blankS=new JLabel(" ");
	        rjp.add(blankS);
	        JLabel otherInfo= new JLabel("               OTHER INFO        ");
	        rjp.add(otherInfo);

	        detjta = new JTextArea(40,4);
	        rjp.add(detjta);
	        JLabel blankk=new JLabel(" ");
	        rjp.add(blankk);
	         
	        
	        JButton makeAppointmentBut= new JButton("             MAKE NOTE         ");
	        makeAppointmentBut.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent ev){
	        		
	        		
//	        		try {
//	        		
//	        		
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
	        	}
	        });
	        rjp.add(makeAppointmentBut);
	        
	        JLabel cata = new JLabel("              CATEGORY A");
	        rjp.add(cata);
	        
	       
	        
	        String[] catA= {"N/A","VND 1","XXV 2","HNF 232","GB334"};
	         catAmjcb = new JComboBox(catA);
	        rjp.add(catAmjcb);
	        
	        JLabel amount = new JLabel("                  Amount:");
	        rjp.add(amount);
	        
	        catAamountjtf=new JTextField("1");
	        rjp.add(catAamountjtf);
	        
	        
	        
	        JLabel catb = new JLabel("              CATEGORY B");
	        rjp.add(catb);
	        
	       
	        
	        
	        
	        
	        String[] catB= {"N/A","X34","HH5","DDF23","JHH7"};
	         catBmjcb = new JComboBox(catB);
	        rjp.add(catBmjcb);
	        
	        JLabel amount1 = new JLabel("                   Amount:");
	        rjp.add(amount1);
	        
	        catBamountjtf=new JTextField("1");
	        rjp.add(catBamountjtf);
	        
	         
	        
	        JLabel catc= new JLabel("               CATEGORY C");
	        rjp.add(catc);
	        String[] catC= {"N/A","543H","344BB","JUY9","232B"};
	         catCmjcb = new JComboBox(catC);
	        rjp.add(catCmjcb);
	        
	        JLabel amount2 = new JLabel("                    Amount:");
	        rjp.add(amount2);
	        
	   
	        
	        catCamountjtf=new JTextField("1");
	        rjp.add(catCamountjtf);
	        
	        //all 
	        
	        //Category A meds cost 150, B cost 100, C cost 70
	        	

	        
	        
	        JButton givemed = new JButton("PRESCRIBE MEDS");
	        rjp.add(givemed);
	        givemed.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent eve){
	        		
	        		String cata,catb,catc;
	        		cata  = catAmjcb.getSelectedItem().toString();
	    			catb  = catBmjcb.getSelectedItem().toString();
	    			catc  = catCmjcb.getSelectedItem().toString();
	        		
	        	 
	        			
	        		
	        			
	        		 
	        		
	        		try {
	        			int row = table.getSelectedRow();
		    			String TID =(table.getModel().getValueAt(row, 0).toString());
		    			String gotten = fnjtf.getText();
		    			String username =fnjtf.getText();
		    			
		    			if(!TID.equals(" ")){
		    				if(!catAmjcb.getSelectedItem().equals("N/A")){
			        			
			        			  aprice = 150;
			        			int Aamount = Integer.parseInt(catAamountjtf.getText());
			        			aprice = Aamount*aprice;
			        			System.out.println(aprice);

			        			
			        			aprice = aprice * Aamount;
			        			
			        			}
		    				if(!catBmjcb.getSelectedItem().equals("N/A")){
			        				
				        			  bprice = 100;
				        			 int Bamount = Integer.parseInt(catBamountjtf.getText());
				        			bprice = bprice * Bamount;
				        			System.out.println(bprice);

				        		}  
		    				if(!catBmjcb.getSelectedItem().equals("N/A")){
				        			
				        			  cprice = 70;
				        			int Camount = Integer.parseInt(catCamountjtf.getText());
				        			System.out.println("gotten from c"+ Camount);
				        			cprice = cprice * Camount;
				        			System.out.println("cpice"+cprice);
				        			
				        		}else{
				        			//JOptionPane.showMessageDialog(null, "SELECT MEDS","NO MEDS CHOSEN",JOptionPane.ERROR_MESSAGE);
				        			
				        		}
		    				
		    			}else{
		    				
		    				
		    			}
		    			
		    			
		    			String allmeds = "CA:"+cata+", "+"CB:"+catb+", "+"CC:"+catc;
		    			int totalprice = aprice+bprice+cprice;
		    			System.out.println("price"+totalprice);
		    			
		    			System.out.println(cata);
		    			
		    			if(username.equals("")){
		    				
						JOptionPane.showMessageDialog(null,"MAKE YOU ENTER MEDS BEFORE TRYING TO PRESCRIBE TO PATIENTS ","MEDICATION NOT GIVEN",JOptionPane.ERROR_MESSAGE);
		    			}else{
		    				s.execute("update patient set meds ='"+allmeds+"',price='"+totalprice+"' where id ='"+TID+"'");
						JOptionPane.showMessageDialog(null,"MEDS PRESCRIBED FOR "+username.toUpperCase(),"MEDICATION GIVEN",JOptionPane.PLAIN_MESSAGE);
		    			}

						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		
	        	}
	        
	        });
	        
	        
	        JLabel inform = new JLabel( " NB: SELECT A PAITENT ON THE TABLE AND THEIR ID WILL BE USED TO COMPLETE ANY ACTIONS");
	        bottom.add(inform);
	        

	        

	        

 
	        
	        
	         this.add(rjp,BorderLayout.LINE_END);
	        rjp.setLayout(new BoxLayout(rjp,BoxLayout.Y_AXIS));
	 		
	 		
	         
	     validate();
	     repaint();
	     
	}
	
	public void cc(){
		this.setVisible(false);
		Oefen eofen = new Oefen();
		eofen.setVisible(true);
	}

	 

	
	
}
