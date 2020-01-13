package BloodBank;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.border.LineBorder;

public class BloodMain extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField age;
	private JTextField address;
	private JTextField phone_number;
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BloodMain frame = new BloodMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	
	Connection c= null;
	private JTable search_table;
	private JTextField nat;  //name
	private JTextField agt;  //age 
	private JTextField bgt; // blood group
	private JTextField adt;  //address
	private JTextField pht;  //phone 
	private JTable update_table;
	private JTextField quantity; //address
	private JTextField qt;
	
	public BloodMain() throws SQLException {
		c=DriverManager.getConnection("JDBC:sqlite:F:\\PROGRAMMING\\java\\BloodBank\\bloodbank.sqlite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1050, 700);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(25, 25, 112));
		contentPane.setBackground(new Color(95, 158, 160));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setFont(new Font("Verdana", Font.BOLD, 16));
		menuBar.setBounds(0, 0, 1032, 26);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setPreferredSize(new Dimension(100, 50));
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tabbedPane.setSelectedIndex(0);
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnFile.add(mntmExit);
		
		JLabel lblBloodDonation = new JLabel("Blood Donation");
		lblBloodDonation.setBackground(new Color(0, 0, 0));
		lblBloodDonation.setForeground(new Color(255, 255, 240));
		lblBloodDonation.setHorizontalAlignment(SwingConstants.CENTER);
		lblBloodDonation.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
		lblBloodDonation.setBounds(0, 39, 1032, 94);
		contentPane.add(lblBloodDonation);
		
		/* tabbed pane */
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBorder(null);
		tabbedPane.setBackground(new Color(245, 255, 250));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 17));
		tabbedPane.setBounds(10, 132, 1010, 508);
		contentPane.add(tabbedPane);
		
		/* add pane  */
		JPanel add = new JPanel();
		add.setBackground(new Color(245, 255, 250));
		tabbedPane.addTab("ADD DONOR", null, add, null);
		add.setFont(new Font("Tahoma", Font.BOLD, 20));
		add.setLayout(null);
		
		JLabel lblAddInformation = new JLabel("Add information");
		lblAddInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddInformation.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblAddInformation.setBounds(12, 13, 878, 42);
		add.add(lblAddInformation);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(58, 68, 196, 27);
		add.add(lblName);
		
		name = new JTextField();
		name.setBackground(new Color(255, 255, 255));
		name.setBounds(281, 68, 478, 27);
		add.add(name);
		name.setColumns(10);
		
		JLabel label = new JLabel("Age");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(58, 119, 196, 27);
		add.add(label);
		
	    age = new JTextField();
	    age.setColumns(10);
	    age.setBounds(281, 119, 478, 27);
	    add.add(age);
	    
	    JLabel label_1 = new JLabel("Blood Group");
	    label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    label_1.setBounds(58, 175, 196, 27);
	    add.add(label_1);
	    
	    JLabel label_2 = new JLabel("Address");
	    label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    label_2.setBounds(58, 232, 196, 27);
	    add.add(label_2);
	    
	    address = new JTextField();
	    address.setColumns(10);
	    address.setBounds(281, 232, 478, 27);
	    add.add(address);
	    
	    JLabel label_3 = new JLabel("Phone number");
	    label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    label_3.setBounds(58, 287, 196, 27);
	    add.add(label_3);
	    
	    phone_number = new JTextField();
	    phone_number.setColumns(10);
	    phone_number.setBounds(281, 287, 478, 27);
	    add.add(phone_number);
	    
	    
	    
	    quantity = new JTextField();
	    quantity.setColumns(10);
	    quantity.setBounds(281, 342, 478, 27);
	    add.add(quantity);
	    
	    JLabel lblQuantity = new JLabel("Quantity");
	    lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    lblQuantity.setBounds(58, 342, 196, 27);
	    add.add(lblQuantity);
	    
	    JComboBox <String> comboBox = new JComboBox<>();
	    comboBox.setForeground(new Color(0, 0, 0));
	    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    comboBox.setToolTipText("Select your blood group");
	    comboBox.setBackground(new Color(255, 255, 255));
	    comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));
	    comboBox.setBounds(281, 175, 478, 22);
	    add.add(comboBox);
	    
	    JButton submit = new JButton("SUBMIT");
	    submit.setForeground(new Color(255, 255, 255));
	    submit.setBackground(new Color(105, 105, 105));
	    submit.addActionListener(new ActionListener() {
	    	
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    	 String nam= name.getText();
	    	 String addres= address.getText();
	    	 String phone= phone_number.getText();
	    	 //String bg = blood_group.getText().toUpperCase();
	    	 String bg = (String) comboBox.getSelectedItem();
	    	 String ag = age.getText();
	    	 String qn=quantity.getText();
	    	
	    	 String query="insert into blood(Name,Age,BloodGroup,Address,PhoneNumber,Quantity)" + "values('"+nam+"','"+ag+"','"+bg+"','"+addres+"','"+phone+"','"+qn+"')";
	    	 try {
	    		Statement st = c.createStatement();
	    		st.execute(query);
	    		JOptionPane.showMessageDialog(null, "donor added");
	    		
	    		
	    	} catch (SQLException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	 name.setText("");
	    	 address.setText("");
	    	 phone_number.setText("");
	    	 age.setText("");
	    	 quantity.setText("");
	    	 //blood_group.setText("");
	    	 
	    	 }
	    });
	    submit.setFont(new Font("Verdana", Font.BOLD, 12));
	    submit.setBounds(442, 398, 97, 32);
	    add.add(submit);
		
	    
	    /* search pane  */ 
		JPanel search = new JPanel();
		search.setBackground(new Color(245, 255, 250));
		tabbedPane.addTab("SEARCH", null, search, null);
		search.setFont(new Font("Times New Roman", Font.BOLD, 20));
		search.setLayout(null);
		
		JLabel label_4 = new JLabel("Blood Group");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_4.setBounds(98, 57, 196, 27);
		search.add(label_4);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 156, 878, 334);
		search.add(scrollPane);
		
		search_table = new JTable();
		scrollPane.setViewportView(search_table);
		
		JComboBox <String> searchComboBox = new JComboBox<>();
		searchComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchComboBox.setBackground(Color.WHITE);
		searchComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));
		searchComboBox.setBounds(254, 61, 350, 22);
		search.add(searchComboBox);
		
		JButton send = new JButton("SEARCH");
		send.setForeground(new Color(255, 255, 255));
		send.setBackground(new Color(105, 105, 105));
		send.setFont(new Font("Verdana", Font.BOLD, 12));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			//String bg1 =bgText.getText().toUpperCase();
			String bg1 = (String) searchComboBox.getSelectedItem();
			
			
			String query1="select * from blood where BloodGroup='"+bg1+"'";
			 try {
				Statement st1 = c.createStatement();
				
				ResultSet rs= st1.executeQuery(query1);
				search_table.setModel(DbUtils.resultSetToTableModel(rs));
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
			}
		});
		send.setBounds(349, 109, 97, 33);
		search.add(send);
		
		/* update pane  */
		
		JPanel update = new JPanel();
		update.setBackground(new Color(245, 255, 250));
		update.setFont(new Font("Times New Roman", Font.BOLD, 20));
		tabbedPane.addTab("UPDATE", null, update, null);
		update.setLayout(null);
		
		JLabel label_5 = new JLabel("Name");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_5.setBounds(12, 13, 196, 27);
		update.add(label_5);
		
		nat = new JTextField();
		nat.setEditable(false);
		nat.setColumns(10);
		nat.setBounds(235, 13, 478, 27);
		update.add(nat);
		
		JLabel label_6 = new JLabel("Age");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_6.setBounds(12, 53, 196, 27);
		update.add(label_6);
		
		agt = new JTextField();
		agt.setColumns(10);
		agt.setBounds(235, 53, 478, 27);
		update.add(agt);
		
		JLabel label_7 = new JLabel("Blood Group");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_7.setBounds(12, 93, 196, 27);
		update.add(label_7);
		
		bgt = new JTextField();
		bgt.setColumns(10);
		bgt.setBounds(235, 93, 478, 27);
		update.add(bgt);
		
		JLabel label_8 = new JLabel("Address");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_8.setBounds(12, 133, 196, 27);
		update.add(label_8);
		
		adt = new JTextField();
		adt.setColumns(10);
		adt.setBounds(235, 133, 478, 27);
		update.add(adt);
		
		JLabel label_9 = new JLabel("Phone number");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_9.setBounds(12, 173, 196, 27);
		update.add(label_9);
		
		pht = new JTextField();
		pht.setColumns(10);
		pht.setBounds(235, 173, 478, 27);
		update.add(pht);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBackground(new Color(105, 105, 105));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nam= nat.getText();
		    	 String addres= adt.getText();
		    	 String phone= pht.getText();
		    	 String bg = bgt.getText();
		    	 String ag = agt.getText();
		    	 String q=qt.getText();
		    	
		    	 String query4="Update blood Set Name='"+ nam +"',Age='"+ ag + "',BloodGroup='"+ bg + "',Address='"+addres+"',PhoneNumber='"+phone + "',Quantity='"+q+"' where Name='" +nam+"'";
		    	 try {
		    		Statement st4= c.createStatement();
		    		st4.execute(query4);
		    		JOptionPane.showMessageDialog(null, "donor updated");
		    		
		    		
		    	} catch (SQLException e) {
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	}
			}
		});
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 12));
		btnUpdate.setBounds(292, 253, 97, 27);
		update.add(btnUpdate);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 291, 878, 199);
		update.add(scrollPane_1);
		
		update_table = new JTable();
		update_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int row=update_table.getSelectedRow();
				String n = update_table.getModel().getValueAt(row, 0).toString();
				try {
					Statement st3 =c.createStatement();
					String query3="select * from blood where Name='"+n+"'";
					ResultSet rs3=st3.executeQuery(query3);
					
					while(rs3.next()) {
						
						nat.setText(rs3.getString("Name"));
						agt.setText(rs3.getString("Age"));
						adt.setText(rs3.getString("Address"));
						pht.setText(rs3.getString("PhoneNumber"));
						bgt.setText(rs3.getString("BloodGroup"));
						qt.setText(rs3.getString("Quantity"));
						
						
					}
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		scrollPane_1.setViewportView(update_table);
		
		JButton btnShow = new JButton("SHOW");
		btnShow.setForeground(new Color(255, 255, 255));
		btnShow.setBackground(new Color(105, 105, 105));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 try {
					 	String query2="select * from blood";
						Statement st2 = c.createStatement();
						
						ResultSet rs2= st2.executeQuery(query2);
						update_table.setModel(DbUtils.resultSetToTableModel(rs2));
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		btnShow.setFont(new Font("Verdana", Font.BOLD, 12));
		btnShow.setBounds(570, 253, 97, 27);
		update.add(btnShow);
		
		qt = new JTextField();
		qt.setColumns(10);
		qt.setBounds(235, 213, 478, 27);
		update.add(qt);
		
		JLabel label_10 = new JLabel("Quantity");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_10.setBounds(12, 213, 196, 27);
		update.add(label_10);
		
	}
}
