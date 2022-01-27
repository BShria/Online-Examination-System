import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class StudentLogin extends JFrame {

	private JPanel contentPane;
	private JTextField sidInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentLogin frame = new StudentLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		lblNewLabel.setBounds(6, 5, 373, 103);
		contentPane.add(lblNewLabel);
		
		sidInput = new JTextField();
		sidInput.setHorizontalAlignment(SwingConstants.CENTER);
		sidInput.setFont(new Font("Helvetica", Font.PLAIN, 18));
		sidInput.setColumns(10);
		sidInput.setBounds(68, 157, 250, 38);
		contentPane.add(sidInput);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = OracleConnection.getDbConnection();
        		PreparedStatement st = null;
        		ResultSet rs = null;
        		
        		try {
        			String sid = sidInput.getText();
        			String query = "SELECT * FROM Student WHERE s_id = ?";
        			st = con.prepareStatement(query);
        			st.setString(1, sid);
        			rs = st.executeQuery();
        			if(rs.next()) {
        				dispose();
        				st = con.prepareStatement("SELECT c_name FROM Course WHERE c_id = '" + rs.getString(4) + "'");
        				ResultSet rs2 = st.executeQuery();
        				rs2.next();
        				
        				st = con.prepareStatement("SELECT e_id, title, TO_CHAR(exam_date,'DD-MM-YYYY') FROM Examination WHERE c_id = '" + rs.getString(4) + "'");
        				ResultSet rs3 = st.executeQuery();     	
        				
        				int appeared = rs.getString(5) == null? -1: rs.getInt(5);
        				
        				if(rs3.next()) {		
        					new StudentDashboard(sid,rs.getString(2), rs.getString(3), rs2.getString(1), rs3.getString(1), rs3.getString(2), rs3.getString(3), appeared).setVisible(true);
        				}
        				else {
        					new StudentDashboard(sid,rs.getString(2), rs.getString(3), rs2.getString(1),null, null, null, appeared).setVisible(true);
        				}
        			}else {
        				JOptionPane.showMessageDialog(new Frame(), "Invalid Student ID! Please enter again.");
        			}
        		}catch (SQLException sqle) {
        			System.out.println(sqle);
        		}
        		finally {
        			try {
        				rs.close();
        				st.close();
        				con.close();
        			}catch (SQLException sqle) {
        				System.out.println(sqle);
        			}
        		}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setBounds(131, 232, 123, 38);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 450, 5);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 191, 255));
		panel_1.setBounds(68, 79, 250, 2);
		contentPane.add(panel_1);
		
		JLabel lblUsername = new JLabel("Student ID:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.GRAY);
		lblUsername.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblUsername.setBounds(114, 106, 158, 37);
		contentPane.add(lblUsername);
	}
}
