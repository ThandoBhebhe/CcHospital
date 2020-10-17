import javax.swing.JMenuBar;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.PreparedStatement;

public class Oefen extends JFrame{
	Connection conn = null;
	PreparedStatement ps =null;
    ResultSet rs = null;
    JTextField njtf = null;
    JTextField pjtf = null;
    String type =null;
    String userid=null;
    
    String username;
    String password;
	
	public String getUsername(String username) {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static void main(String[]args){
		try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Oefen();
	}
	
	//Default constructor
	public Oefen(){
		
		connect();
		gui();
			
		}
	
	//GUI mainFrame
	public void gui(){
		
		setTitle("CiCELL HOSPITAL");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		setSize(500,150);
		this.setLocationRelativeTo(null);
		
		//JMenuBar complete//////////////////////
		
		JMenuBar jmb = new JMenuBar();
		this.setJMenuBar(jmb);
		
		JMenu fileOption = new JMenu("File");
		jmb.add(fileOption);
		
		JMenuItem save = new JMenuItem("Save");
		fileOption.add(save);
		
		//BorderLayout, Buttons Panels/////////////////
		this.setLayout(new BorderLayout());
		JPanel jp = new JPanel();

		JButton jb = new JButton("LOGIN");				
		//jp.setBorder(BorderFactory.createLineBorder(Color.black));

		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args){
				try {
					login();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
			
		});
		
		//textField, adding JPanels etc
		jp.add(jb);
		this.add(jp,BorderLayout.PAGE_END);
		JPanel jpd = new JPanel();
		JLabel njl = new JLabel("NAME:");
		jpd.add(njl);
		this.add(jpd,BorderLayout.LINE_START);
		njtf = new JTextField(13);
		jpd.add(njtf);
		njtf.setBackground(Color.gray);
		pjtf = new JTextField(13);
		pjtf.setBackground(Color.gray);
		JLabel pjl = new JLabel("PASSWORD:");
		JPanel jpc = new JPanel();	
		jpc.setLayout(new FlowLayout());

		jpc.add(pjl);
		jpc.add(pjtf);
		this.add(jpc,BorderLayout.CENTER);
		
		JButton exit = new JButton("EXIT");
		jp.add(exit);
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent args0){
				
				int choice = JOptionPane.showConfirmDialog(null, 
						   "Are you sure you wish to exit application?",null, JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION) {
						    System.exit(0);
					}
				
			}
		
			
		});
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);       
		this.repaint();
		this.validate();
	}
	
	//CONNECTION METHOD///////////////////
	public void connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","");
			JOptionPane.showMessageDialog(this, "Connection Succesful...","SUCCESS",JOptionPane.PLAIN_MESSAGE);
			this.setVisible(true);
			
		}catch(Exception e){
			
			JOptionPane.showMessageDialog(this, "Error Connecting to database: Contact IT","Connection Error",JOptionPane.ERROR_MESSAGE);
			System.out.println(e);//printing error to console
			this.setVisible(true);
			//e.printStackTrace();
			if(e.equals("Communications link failure The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.")){
				//JOptionPane.showMessageDialog(this, "Error Connecting to database: turn on xammp ","Connection Error",JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(this, e,"Connection Error",JOptionPane.ERROR_MESSAGE);

			}
		}
		
	}
	
	public void login() throws ParseException{
		
		String upi = pjtf.getText().trim();
		this.password=upi;
		
	    String uni = njtf.getText().trim();
		this.username=uni;

		try{
		ps = conn.prepareStatement("select * from users where username =? and password = ?");
		
		ps.setString(1, uni);
	
		ps.setString(2, upi);
		//System.out.println(upi+uni);
		
		rs = ps.executeQuery();
		int count = 0;
		
		while(rs.next()){
			
			//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			type=rs.getString(4);
			userid =rs.getString(1);//couldnt use this string
			
			/**I was tying to get the user id so i access use it in the OefPat (patients class) using getters
			 *  and setters from but every time i create an instance of this class in my patient class it just 
			 *  pops up even when i did not  set it to visible. It would then start over and ask for credentials 
			 *  whilst the patient gui is open
			 *  
			 *  after asked for credentials again it would now return the userid as null which wasnt of use...
			 *  
			 * this was so i could use an sql statement to UPDATE table and set the calls to whatever they type in
			 * call records WHERE the userid is whatever theyre userID was
			*/
			//System.out.println("user id is"+userid);
			count++;
		}
		
			//checking if the user exists in the database and only once
			if(count == 1){
				JOptionPane.showMessageDialog(this, "LOGGIN IN","CORRECT DETAILS",JOptionPane.PLAIN_MESSAGE);
			
				if (type.equals("doctor")){
					OefDoc docs = new OefDoc();
					
					docs.setVisible(true);
				}else if(type.equals("patient")){
					OefPat pat = new OefPat();
					pat.setVisible(true);
				}else if (type.equals("receptionist")){
					OefRec reception = new OefRec();
					reception.setVisible(true);
				}else if(type.equals("biller")){
					OefBill oefbill = new OefBill();
					oefbill.setVisible(true);
				
				}
			
			this.setVisible(false);
			
			//closing connection if login was successful
			try {
			conn.close();
			//System.out.println("connection closed");
			
		} catch (SQLException closing) {
			
			closing.printStackTrace();
		}
			
			//checking if user exits more then once
			}else if(count > 1){
				JOptionPane.showMessageDialog(this, "USER EXISTS MORE THAN ONCE IN THE DATABASE, CONTACT IT FOR SUPPORT","DUPLICATE USER",JOptionPane.ERROR_MESSAGE);
			
			//wrong details
			}else {
				//UIManager.put("OptionPane.minimumSize", new Dimension(500,100));
				JOptionPane.showMessageDialog(this, "ENTER CORRECT DETAILS","WRONG DETAILS",JOptionPane.ERROR_MESSAGE);
			}
		
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
