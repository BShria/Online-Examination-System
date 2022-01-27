import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPanel panel;
	private JPanel panel_1;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 400, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 5, 373, 103);
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
		contentPane.add(lblNewLabel);
		
		username = new JTextField();
		username.setHorizontalAlignment(SwingConstants.CENTER);
		username.setFont(new Font("Helvetica", Font.PLAIN, 18));
		username.setBounds(68, 157, 250, 38);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username.getText().equals("admin") && new String(password.getPassword()).equals("admin"))
        		{
        			dispose();
        			new AdminDashboard().setVisible(true);
        		}else {
        			JOptionPane.showMessageDialog(new Frame(), "Invalid credentials! Please enter again.");
        		}
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 191, 255));
		btnNewButton.setFont(new Font("Helvetica", Font.PLAIN, 18));
		btnNewButton.setBounds(131, 331, 123, 38);
		contentPane.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 450, 5);
		contentPane.add(panel);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 191, 255));
		panel_1.setBounds(68, 79, 250, 2);
		contentPane.add(panel_1);
		
		password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setFont(new Font("Helvetica", Font.PLAIN, 18));
		password.setBounds(68, 264, 250, 38);
		contentPane.add(password);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(128, 128, 128));
		lblPassword.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblPassword.setBounds(114, 217, 158, 37);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.GRAY);
		lblUsername.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblUsername.setBounds(114, 106, 158, 37);
		contentPane.add(lblUsername);
	}
}
