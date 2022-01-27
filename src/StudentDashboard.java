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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class StudentDashboard extends JFrame {

	private JPanel contentPane;
	private ResultSet quizrs = null;
	private Connection con = null;
	private PreparedStatement st = null;
	private JPanel quizPanel;
	private JTextPane question;
	private JLabel qno;
	private JRadioButton opt1, opt2, opt3, opt4 ;
	private int countMarks=0, quesNo=0;
	private JButton btnNext;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashboard frame = new StudentDashboard("", "","","","","","",-1);
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
	public StudentDashboard(String stid, String stName, String stEmail, String stCourse,String exId, String exam, String edate, int appeared) {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1150, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel resultPanel = new JPanel();
		resultPanel.setLayout(null);
		resultPanel.setBorder(null);
		resultPanel.setBackground(new Color(0, 191, 255));
		resultPanel.setBounds(590, 214, 343, 230);
		contentPane.add(resultPanel);
		resultPanel.setVisible(false);
		
		
		JLabel greeting = new JLabel("Thank You For Appearing");
		greeting.setHorizontalAlignment(SwingConstants.CENTER);
		greeting.setForeground(Color.WHITE);
		greeting.setFont(new Font("Helvetica", Font.BOLD, 23));
		greeting.setBounds(20, 69, 302, 51);
		resultPanel.add(greeting);
		
		JLabel marksLabel = new JLabel("");
		marksLabel.setForeground(new Color(255, 255, 255));
		marksLabel.setHorizontalAlignment(SwingConstants.CENTER);
		marksLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
		marksLabel.setBounds(92, 128, 158, 31);
		resultPanel.add(marksLabel);
//		if student has already appeared for the test then appeared will contain the marks scored otherwise -1
		if(appeared != -1) {
			resultPanel.setVisible(true);
			marksLabel.setText("Your Score: " + appeared);
		}
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBorder(null);
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 383, 684);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(50, 62, 144, 36);
		lblNewLabel.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 26));
		panel.add(lblNewLabel);
		
		JLabel name = new JLabel(stName);
		name.setForeground(new Color(255, 255, 255));
		name.setFont(new Font("Helvetica", Font.PLAIN, 34));
		name.setBounds(50, 108, 387, 57);
		panel.add(name);
		
		JLabel lblNewLabel_2 = new JLabel("Student ID:");
		lblNewLabel_2.setForeground(new Color(248, 248, 255));
		lblNewLabel_2.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblNewLabel_2.setBounds(50, 196, 107, 36);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Course:");
		lblNewLabel_2_1.setForeground(new Color(248, 248, 255));
		lblNewLabel_2_1.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblNewLabel_2_1.setBounds(50, 242, 82, 36);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Email:");
		lblNewLabel_2_1_1.setForeground(new Color(248, 248, 255));
		lblNewLabel_2_1_1.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		lblNewLabel_2_1_1.setBounds(50, 288, 67, 36);
		panel.add(lblNewLabel_2_1_1);
		
		JLabel sid = new JLabel(stid);
		sid.setForeground(new Color(248, 248, 255));
		sid.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		sid.setBounds(157, 196, 107, 36);
		panel.add(sid);
		
		JLabel courseName = new JLabel(stCourse);
		courseName.setForeground(new Color(248, 248, 255));
		courseName.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		courseName.setBounds(127, 242, 234, 36);
		panel.add(courseName);
		
		JLabel email = new JLabel(stEmail);
		email.setForeground(new Color(248, 248, 255));
		email.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		email.setBounds(111, 288, 182, 36);
		panel.add(email);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(50, 175, 299, 2);
		panel.add(panel_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(240, 248, 255), 2));
		panel_3.setBackground(new Color(0, 191, 255));
		panel_3.setBounds(50, 595, 107, 36);
		panel.add(panel_3);
		
		JLabel lblSignOut = new JLabel("Sign Out");
		lblSignOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Form().setVisible(true);
			}
		});
		lblSignOut.setForeground(new Color(248, 248, 255));
		lblSignOut.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		panel_3.add(lblSignOut);
		
		JPanel examScheduledPanel = new JPanel();
		examScheduledPanel.setBorder(null);
		examScheduledPanel.setBackground(new Color(0, 191, 255));
		examScheduledPanel.setBounds(590, 214, 343, 230);
		contentPane.add(examScheduledPanel);
		examScheduledPanel.setLayout(null);
		if(appeared != -1) {
			examScheduledPanel.setVisible(false);
		}
		
		JLabel examTitle = new JLabel("No Exam Scheduled");
		if(exam != null) {
			examTitle.setText(exam);
		}
		examTitle.setForeground(new Color(255, 255, 255));
		examTitle.setHorizontalAlignment(SwingConstants.CENTER);
		examTitle.setBounds(20, 50, 302, 31);
		examTitle.setFont(new Font("Helvetica", Font.BOLD, 30));
		examScheduledPanel.add(examTitle);
		
		
		
		JLabel examDate = new JLabel("");
		if(edate != null) {
			examDate.setText(edate);
		}
		examDate.setHorizontalAlignment(SwingConstants.CENTER);
		examDate.setFont(new Font("Helvetica", Font.PLAIN, 16));
		examDate.setBounds(100, 97, 142, 17);
		examScheduledPanel.add(examDate);
		
		quizPanel = new JPanel();
		quizPanel.setBorder(new LineBorder(new Color(0, 191, 255), 1, true));
		quizPanel.setBackground(Color.WHITE);
		quizPanel.setBounds(404, 27, 694, 625);
		contentPane.add(quizPanel);
		quizPanel.setLayout(null);
		quizPanel.setVisible(false);
		
		question = new JTextPane();
		question.setEditable(false);
		question.setFont(new Font("Helvetica", Font.PLAIN, 21));
		question.setText("Test");
		question.setBounds(32, 139, 629, 112);
		quizPanel.add(question);
		
		qno = new JLabel("Question 1");
		qno.setForeground(new Color(0, 191, 255));
		qno.setFont(new Font("Helvetica", Font.PLAIN, 19));
		qno.setBounds(32, 65, 156, 40);
		quizPanel.add(qno);
		
		opt1 = new JRadioButton("");
		opt1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				opt2.setSelected(false);
				opt3.setSelected(false);
				opt4.setSelected(false);
			}
		});
		opt1.setFont(new Font("Helvetica", Font.PLAIN, 20));
		opt1.setBackground(Color.WHITE);
		opt1.setBounds(32, 258, 248, 21);
		quizPanel.add(opt1);
		
		opt2 = new JRadioButton("");
		opt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				opt1.setSelected(false);
				opt3.setSelected(false);
				opt4.setSelected(false);
			}
		});
		opt2.setFont(new Font("Helvetica", Font.PLAIN, 20));
		opt2.setBackground(Color.WHITE);
		opt2.setBounds(32, 303, 248, 21);
		quizPanel.add(opt2);
		
		opt3 = new JRadioButton("");
		opt3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				opt2.setSelected(false);
				opt1.setSelected(false);
				opt4.setSelected(false);
			}
		});
		opt3.setFont(new Font("Helvetica", Font.PLAIN, 20));
		opt3.setBackground(Color.WHITE);
		opt3.setBounds(32, 354, 248, 21);
		quizPanel.add(opt3);
		
		opt4 = new JRadioButton("");
		opt4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				opt2.setSelected(false);
				opt3.setSelected(false);
				opt1.setSelected(false);
			}
		});
		opt4.setFont(new Font("Helvetica", Font.PLAIN, 20));
		opt4.setBackground(Color.WHITE);
		opt4.setBounds(32, 406, 248, 21);
		quizPanel.add(opt4);
		
		
		
		
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(marks);
				quizPanel.setVisible(false);
				resultPanel.setVisible(true);
				marksLabel.setText("Your Score: " + countMarks);
				try {
					Statement stMarks =con.createStatement();
					stMarks.executeUpdate("UPDATE Student SET marks = '" + countMarks + "' WHERE s_id = '" + stid + "'");
				}catch (Exception exp) {
					System.out.println(exp);
				}
				finally {
					try {
						quizrs.close();
						st.close();
						con.close();
					}catch (Exception exp) {
						System.out.println(exp);
					}
				}
			}
		});
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBackground(new Color(0, 191, 255));
		btnSubmit.setFont(new Font("Helvetica", Font.PLAIN, 19));
		btnSubmit.setBounds(197, 528, 112, 38);
		quizPanel.add(btnSubmit);
		
		
		JButton startExamBtn = new JButton("Start Test");
		startExamBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quizPanel.setVisible(true);
				examScheduledPanel.setVisible(false);
				
        		try {
        			con = OracleConnection.getDbConnection();
        			String query = "SELECT * FROM Quiz WHERE q_id IN (SELECT q_id FROM Contains WHERE e_id = ?)";
        			st = con.prepareStatement(query);
        			st.setString(1, exId);
        			quizrs = st.executeQuery();
        			if(quizrs.next()) {
        				setDataToQuiz(quizrs);
        			}else {
        				btnNext.setEnabled(false);
        			}
        		}catch (Exception sqle) {
        			System.out.println(sqle);
        		}				
			}
		});
		startExamBtn.setEnabled(false);
		startExamBtn.setForeground(new Color(255, 255, 255));
		startExamBtn.setBackground(new Color(0, 51, 102));
		startExamBtn.setFont(new Font("Helvetica", Font.PLAIN, 18));
		startExamBtn.setBounds(104, 139, 134, 45);
		startExamBtn.setFocusPainted(false);
		examScheduledPanel.add(startExamBtn);
		if(exam != null) {
			Date dt = new Date();
			SimpleDateFormat sdt = new SimpleDateFormat("DD-MM-yyyy");
			if(edate.equals(sdt.format(dt))) {
				startExamBtn.setEnabled(true);
			}
		}
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ans = "";
					if(opt1.isSelected()) {
						ans = opt1.getText();
					}else if(opt2.isSelected()) {
						ans = opt2.getText();
					}else if(opt3.isSelected()) {
						ans = opt3.getText();
					}else if(opt4.isSelected()) {
						ans = opt4.getText();
					}
					
					if(quizrs.getString(7).equals(ans)) {
						countMarks++;
					}
					if(quizrs.next()) {
	    				setDataToQuiz(quizrs);
	    			}else {
	    				btnNext.setEnabled(false);
	    			}
				}catch (Exception exp) {
					System.out.println(exp);
				}
			}
		});
		btnNext.setForeground(new Color(255, 255, 255));
		btnNext.setBackground(new Color(0, 191, 255));
		btnNext.setFont(new Font("Helvetica", Font.PLAIN, 19));
		btnNext.setBounds(34, 528, 112, 38);
		quizPanel.add(btnNext);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		panel_2.setBounds(379, 0, 800, 5);
		contentPane.add(panel_2);
	}
	
	public void setDataToQuiz(ResultSet rs) throws Exception{
		quesNo++;
		qno.setText("Question " + quesNo);
		
		question.setText(rs.getString(2));
		opt1.setText(rs.getString(3));
		opt1.setSelected(false);
		opt2.setText(rs.getString(4));
		opt2.setSelected(false);
		opt3.setText(rs.getString(5));
		opt3.setSelected(false);
		opt4.setText(rs.getString(6));
		opt4.setSelected(false);
	}
}
