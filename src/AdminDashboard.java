import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminDashboard extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel title;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
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
	public AdminDashboard() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 50, 1150, 720);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addQuestionBtn = new JButton("Add Question");
		addQuestionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuestionForm("ADD").setVisible(true);
			}
		});
		addQuestionBtn.setForeground(new Color(255, 255, 255));
		addQuestionBtn.setBackground(new Color(0, 191, 255));
		addQuestionBtn.setBounds(295, 542, 169, 39);
		addQuestionBtn.setFont(new Font("Helvetica", Font.BOLD, 17));
		contentPane.add(addQuestionBtn);
		addQuestionBtn.setVisible(false);
		
		JButton updateQuestionBtn = new JButton("Update Question");
		updateQuestionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuestionForm("UPDATE").setVisible(true);
			}
		});
		updateQuestionBtn.setForeground(new Color(255, 255, 255));
		updateQuestionBtn.setBackground(new Color(0, 191, 255));
		updateQuestionBtn.setFont(new Font("Helvetica", Font.BOLD, 17));
		updateQuestionBtn.setBounds(494, 542, 195, 39);
		contentPane.add(updateQuestionBtn);
		updateQuestionBtn.setVisible(false);
		
		JButton delQuestionBtn = new JButton("Delete Question");
		delQuestionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuestionForm("DELETE").setVisible(true);
			}
		});
		delQuestionBtn.setForeground(new Color(255, 255, 255));
		delQuestionBtn.setBackground(new Color(0, 191, 255));
		delQuestionBtn.setFont(new Font("Helvetica", Font.BOLD, 17));
		delQuestionBtn.setBounds(717, 542, 169, 39);
		contentPane.add(delQuestionBtn);
		delQuestionBtn.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 270, 684);
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBorder(null);
		panel.setBackground(new Color(0, 191, 255));
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 26));
		lblNewLabel.setBounds(50, 62, 144, 36);
		panel.add(lblNewLabel);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setForeground(Color.WHITE);
		lblAdmin.setFont(new Font("Helvetica", Font.PLAIN, 34));
		lblAdmin.setBounds(50, 108, 323, 57);
		panel.add(lblAdmin);
		
		JLabel studentLabel = new JLabel("All Students");
		studentLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTableData("Student Details:", "Student");
				addQuestionBtn.setVisible(false);
				updateQuestionBtn.setVisible(false);
				delQuestionBtn.setVisible(false);			
			}
		});
		studentLabel.setForeground(new Color(248, 248, 255));
		studentLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		studentLabel.setBounds(50, 196, 107, 36);
		panel.add(studentLabel);
		
		JLabel examLabel = new JLabel("Examinations");
		examLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTableData("Scheduled Examinations:", "Examination");
				addQuestionBtn.setVisible(false);
				updateQuestionBtn.setVisible(false);
				delQuestionBtn.setVisible(false);	
			}
		});
		examLabel.setForeground(new Color(248, 248, 255));
		examLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		examLabel.setBounds(50, 242, 144, 36);
		panel.add(examLabel);
		
		JLabel questionLabel = new JLabel("Questions");
		questionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setTableData("All Questions:", "Quiz");
				addQuestionBtn.setVisible(true);
				updateQuestionBtn.setVisible(true);
				delQuestionBtn.setVisible(true);
			}
		});
		questionLabel.setForeground(new Color(248, 248, 255));
		questionLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		questionLabel.setBounds(50, 288, 144, 36);
		panel.add(questionLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.WHITE);
		panel_1.setBorder(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(50, 174, 193, 3);
		panel.add(panel_1);
		
		JLabel logoutLabel = new JLabel("Sign Out");
		logoutLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Form().setVisible(true);
			}
		});
		logoutLabel.setForeground(new Color(248, 248, 255));
		logoutLabel.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		logoutLabel.setBounds(50, 334, 144, 36);
		panel.add(logoutLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(295, 158, 820, 331);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table = new JTable();
		table.setFont(new Font("Helvetica", Font.PLAIN, 10));
		JTableHeader tHeader = table.getTableHeader();
		tHeader.setBackground(new Color(0x00bfff));
		tHeader.setFont(new Font("Helvetica Rounded", Font.PLAIN, 18));
		tHeader.setForeground(Color.white);
		table.setFont(new Font("Helvetica", Font.PLAIN, 15));
		table.setRowHeight(30);
		scrollPane.setViewportView(table);
		
		title = new JLabel("");
		title.setBounds(296, 73, 439, 38);
		title.setForeground(new Color(0, 191, 255));
		title.setFont(new Font("Helvetica", Font.BOLD, 25));
		contentPane.add(title);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(295, 110, 299, 2);
		panel_1_1.setForeground(Color.WHITE);
		panel_1_1.setBorder(null);
		panel_1_1.setBackground(new Color(0, 191, 255));
		contentPane.add(panel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 191, 255));
		panel_2.setBounds(269, 0, 900, 5);
		contentPane.add(panel_2);
		
		
		setTableData("Student Details:", "Student");
	}
	
	public void setTableData(String heading, String tableName) {
		Connection con = OracleConnection.getDbConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			title.setText(heading);
			st = con.createStatement();
			rs = st.executeQuery("select * from " + tableName);
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException sqle) {
			System.out.println(sqle);
		}
		finally {
			try {
				rs.close();
				st.close();
				con.close();
			}
			catch (SQLException sqle) {
				System.out.println(sqle);
			}
		}
	}
}
