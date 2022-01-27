import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Form extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
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
	public Form() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 738, 499);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Online Examination System");
		lblNewLabel.setForeground(new Color(0, 191, 255));
		lblNewLabel.setBounds(5, 5, 714, 100);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Helvetica", Font.BOLD, 37));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\shria\\eclipse-workspace\\Online-Examination-System\\Icons\\Form.png"));
		lblNewLabel_1.setBounds(87, 115, 279, 249);
		contentPane.add(lblNewLabel_1);
		
		JButton adminLoginBtn = new JButton("Admin Login");
		adminLoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminLogin().setVisible(true);
			}
		});
		adminLoginBtn.setForeground(new Color(255, 255, 255));
		adminLoginBtn.setBackground(new Color(0, 191, 255));
		adminLoginBtn.setFont(new Font("Helvetica", Font.PLAIN, 17));
		adminLoginBtn.setBounds(475, 115, 163, 43);
		contentPane.add(adminLoginBtn);
		
		JButton btnStudentLogin = new JButton("Student Login");
		btnStudentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new StudentLogin().setVisible(true);
			}
		});
		btnStudentLogin.setForeground(new Color(255, 255, 255));
		btnStudentLogin.setBackground(new Color(0, 191, 255));
		btnStudentLogin.setFont(new Font("Helvetica", Font.PLAIN, 17));
		btnStudentLogin.setBounds(475, 210, 163, 43);
		contentPane.add(btnStudentLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 248, 255));
		panel_1.setBounds(473, 311, 199, 100);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Developed By:");
		lblNewLabel_2.setBounds(0, 0, 129, 25);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_2_1 = new JLabel("Anuj Kumar Mahli (01)");
		lblNewLabel_2_1.setBounds(0, 28, 168, 25);
		panel_1.add(lblNewLabel_2_1);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Pamela Sithar Wangmo (03)");
		lblNewLabel_2_1_1.setBounds(0, 52, 215, 25);
		panel_1.add(lblNewLabel_2_1_1);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Shria Banerjee (09)");
		lblNewLabel_2_1_1_1.setBounds(0, 75, 215, 25);
		panel_1.add(lblNewLabel_2_1_1_1);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 725, 5);
		contentPane.add(panel);
	}
}
