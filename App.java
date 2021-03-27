package swingcrud;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class App {

	private JFrame frame;
	private JTextField textField_Id;
	private JTextField textField_Fname;
	private JTextField textField_Lname;
	private JTextField textField_Age;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
		connect();
		table_load();
	}
	   Connection con;
	    PreparedStatement st;
	    ResultSet rs;
	public void connect() {
		 try {
             Class.forName("com.mysql.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "root");
		   } catch (ClassNotFoundException | SQLException ex) {
               Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
           }
            	 
           
	}
	public void table_load() {
	try {
		st = con.prepareStatement("select * from employee");
		rs =st.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		
	}
	catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 662, 314);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 62, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_Id = new JTextField();
		textField_Id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				/*
				 * try {
				 * 
				 * String id = textField_Id.getText();
				 * 
				 * st =
				 * con.prepareStatement("select fname,lname ,age from employee where id = ?");
				 * st.setString(1, id); ResultSet rs = st.executeQuery();
				 * 
				 * if(rs.next()==true) {
				 * 
				 * String fname = rs.getString(1); String lname= rs.getString(2); String age=
				 * rs.getString(3);
				 * 
				 * 
				 * textField_Fname.setText(fname); textField_Lname.setText(lname);
				 * textField_Age.setText(age);
				 * 
				 * 
				 * } else { textField_Id.setText(""); textField_Fname.setText("");
				 * textField_Lname.setText(""); textField_Age.setText("");
				 * 
				 * }
				 * 
				 * 
				 * 
				 * }
				 * 
				 * catch (SQLException ex) {
				 * 
				 * }
				 * 
				 */
			}
		});
		textField_Id.setBounds(66, 59, 93, 20);
		frame.getContentPane().add(textField_Id);
		textField_Id.setColumns(10);
		
		textField_Fname = new JTextField();
		textField_Fname.setColumns(10);
		textField_Fname.setBounds(66, 106, 93, 20);
		frame.getContentPane().add(textField_Fname);
		
		JLabel lblNewLabel_1 = new JLabel("Fname");
		lblNewLabel_1.setBounds(10, 109, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_Lname = new JTextField();
		textField_Lname.setColumns(10);
		textField_Lname.setBounds(66, 154, 93, 20);
		frame.getContentPane().add(textField_Lname);
		
		JLabel lblNewLabel_2 = new JLabel("Lname");
		lblNewLabel_2.setBounds(10, 157, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_Age = new JTextField();
		textField_Age.setColumns(10);
		textField_Age.setBounds(66, 199, 93, 20);
		frame.getContentPane().add(textField_Age);
		
		JLabel lblNewLabel_3 = new JLabel("Age");
		lblNewLabel_3.setBounds(10, 202, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Java Swing Assignment");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 29));
		lblNewLabel_4.setBounds(103, 11, 449, 27);
		frame.getContentPane().add(lblNewLabel_4);
		//save data
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_Id==null||textField_Fname==null||textField_Lname==null||textField_Age==null)
			
				{
					JOptionPane.showMessageDialog(null, "please enter the data correctly!!");	
				}
				else {
				String id,fname,lname,age;
				id=textField_Id.getText();
				fname=textField_Fname.getText();
				lname=textField_Lname.getText();
				age=textField_Age.getText();
				
				try {
					st = con.prepareStatement("insert into employee values(?,?,?,?)");
					st.setString(1, id);
					st.setString(2, fname);
					st.setString(3, lname);
					st.setString(4, age); 
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record added !!");
					table_load();
					textField_Id.setText("");
					textField_Fname.setText("");
					textField_Lname.setText("");
					textField_Age.setText("");
					textField_Id.requestFocus();
					textField_Fname.requestFocus();
					textField_Lname.requestFocus();
					textField_Age.requestFocus();
				}
				catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}
				
				}
			}
		});
		btnNewButton.setBounds(22, 242, 89, 23);
		frame.getContentPane().add(btnNewButton);
		//update code
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id,fname,lname,age;
				id=textField_Id.getText();
				fname=textField_Fname.getText();
				lname=textField_Lname.getText();
				age=textField_Age.getText();
				
				try {
					st = con.prepareStatement("update employee set fname=?,lname=?,age=? where id =?");
					st.setString(1, fname);
					st.setString(2, lname);
					st.setString(3, age); 
					st.setString(4, id);
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!");
					table_load();
					textField_Id.setText("");
					textField_Fname.setText("");
					textField_Lname.setText("");
					textField_Age.setText("");
					textField_Id.requestFocus();
					
				}
				catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}
				
				
			}
		});
		btnNewButton_1.setBounds(153, 242, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		//delete the row;
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String id;
				id=textField_Id.getText();
				
				
				try {
					st = con.prepareStatement("delete from employee where id =?");
					st.setString(1, id);
					 
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted !!");
					table_load();
					textField_Id.setText("");
					
					textField_Id.requestFocus();
					
				}
				catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}
				
			}
		});
		btnNewButton_2.setBounds(292, 242, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 62, 353, 157);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 try {
			           
		         DefaultTableModel model =(DefaultTableModel)table.getModel();
		         int i=table.getSelectedRow();
		         textField_Id.setText(model.getValueAt(i, 0).toString());
					textField_Fname.setText(model.getValueAt(i, 1).toString());
					textField_Lname.setText(model.getValueAt(i, 2).toString());
					textField_Age.setText(model.getValueAt(i, 3).toString());
		         
		         
		         } 
		 
		 catch (Exception ex) {
		            ex.printStackTrace();
		         }
			
			
			}
				
				
			
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Fname", "Lname", "Age"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_3 = new JButton("Clear");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_Id.setText("");
				textField_Fname.setText("");
				textField_Lname.setText("");
				textField_Age.setText("");
				
			}
		});
		btnNewButton_3.setBounds(421, 242, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
	}
}