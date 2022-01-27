import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextArea;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class QuestionForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtQuestionId;
	private JTextField txtOption1;
	private JTextField txtOption2;
	private JTextField txtOption3;
	private JTextField txtOption4;
	private JTextField txtAnswer;
	private JButton Search;
	private JButton updateBtn;
	private JButton delBtn;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionForm frame = new QuestionForm("ADD");
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
	public QuestionForm(String operation) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 450, 589);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 436, 10);
		contentPane.add(panel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(236, 45, 156, 32);
		contentPane.add(scrollPane);
		scrollPane.setVisible(false);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setFont(new Font("Helvetica", Font.PLAIN, 15));
		
		txtQuestionId = new JTextField();
		txtQuestionId.setFont(new Font("Helvetica", Font.PLAIN, 15));
		txtQuestionId.setText("Question ID");
		txtQuestionId.setBounds(33, 45, 134, 32);
		contentPane.add(txtQuestionId);
		txtQuestionId.setColumns(10);
		
		JTextArea questionTxtArea = new JTextArea();
		questionTxtArea.setFont(new Font("Helvetica", Font.PLAIN, 16));
		questionTxtArea.setText("Enter the Question");
		questionTxtArea.setBounds(33, 103, 361, 64);
		questionTxtArea.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		contentPane.add(questionTxtArea);
		
		txtOption1 = new JTextField();
		txtOption1.setText("Option 1");
		txtOption1.setFont(new Font("Helvetica", Font.PLAIN, 15));
		txtOption1.setColumns(10);
		txtOption1.setBounds(33, 190, 232, 32);
		contentPane.add(txtOption1);
		
		txtOption2 = new JTextField();
		txtOption2.setText("Option 2");
		txtOption2.setFont(new Font("Helvetica", Font.PLAIN, 15));
		txtOption2.setColumns(10);
		txtOption2.setBounds(33, 249, 232, 32);
		contentPane.add(txtOption2);
		
		txtOption3 = new JTextField();
		txtOption3.setText("Option 3");
		txtOption3.setFont(new Font("Helvetica", Font.PLAIN, 15));
		txtOption3.setColumns(10);
		txtOption3.setBounds(33, 309, 232, 32);
		contentPane.add(txtOption3);
		
		txtOption4 = new JTextField();
		txtOption4.setText("Option 4");
		txtOption4.setFont(new Font("Helvetica", Font.PLAIN, 15));
		txtOption4.setColumns(10);
		txtOption4.setBounds(33, 368, 232, 32);
		contentPane.add(txtOption4);
		
		txtAnswer = new JTextField();
		txtAnswer.setText("Answer");
		txtAnswer.setFont(new Font("Helvetica", Font.PLAIN, 15));
		txtAnswer.setColumns(10);
		txtAnswer.setBounds(33, 424, 232, 32);
		contentPane.add(txtAnswer);
		
		JButton addBtn = new JButton("Add ");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qid = txtQuestionId.getText();
				String question = questionTxtArea.getText();
				String opt1 = txtOption1.getText();
				String opt2 = txtOption2.getText();
				String opt3 = txtOption3.getText();
				String opt4 = txtOption4.getText();
				String ans = txtAnswer.getText();
				List<String> selectedTitles = list.getSelectedValuesList();
				
				Connection con = OracleConnection.getDbConnection();
				Statement st = null;
				ResultSet eid = null;
				try {
					String query = "INSERT INTO Quiz VALUES ('"+qid+"','"+question+"','"+opt1+"','"+opt2+"','"+opt3+"','"+opt4+"','"+ans+"')";
					st = con.createStatement();
					st.executeUpdate(query);
					
					for(String eTitle: selectedTitles) {
						query = "SELECT e_id FROM Examination WHERE title = '" + eTitle + "'";
						eid = st.executeQuery(query);
						if(eid.next()) {
							eid.getString(1);
							query = "INSERT INTO Contains VALUES ('" + eid.getString(1) + "', '" + qid + "')";
							st.executeUpdate(query);
						}
					}
					
					dispose();
					JOptionPane.showMessageDialog(new Frame(), "Question added successfully.");
					
				}
				catch (SQLException sqle) {
					System.out.println(sqle);
				}
				finally {
					try {
						eid.close();
						st.close();
						con.close();
					}
					catch (SQLException sqle) {
						System.out.println(sqle);
					}
				}
			}
		});
		
		
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qid = txtQuestionId.getText();
				String question = questionTxtArea.getText();
				String opt1 = txtOption1.getText();
				String opt2 = txtOption2.getText();
				String opt3 = txtOption3.getText();
				String opt4 = txtOption4.getText();
				String ans = txtAnswer.getText();
				
				Connection con = OracleConnection.getDbConnection();
				Statement st = null;
				try {
					String query = "UPDATE Quiz SET question = '"+question+"', option1 = '"+opt1+"', option2 = '"+opt2+"', option3 = '"+opt3+"', option4 = '"+opt4+"', answer = '"+ans+"' WHERE q_id = '" + qid + "'";
					st = con.createStatement();
					st.executeUpdate(query);
					dispose();
					JOptionPane.showMessageDialog(new Frame(), "Question updated successfully.");
				}
				catch (SQLException sqle) {
					System.out.println(sqle);
				}
				finally {
					try {
						st.close();
						con.close();
					}
					catch (SQLException sqle) {
						System.out.println(sqle);
					}
				}
			}
		});
		
		
		delBtn = new JButton("Delete");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qid = txtQuestionId.getText();
				Connection con = OracleConnection.getDbConnection();
				Statement st = null;
				try {
					String query = "DELETE FROM Quiz WHERE q_id = '" + qid + "'";
					st = con.createStatement();
					st.executeUpdate(query);
					dispose();
					JOptionPane.showMessageDialog(new Frame(), "Question deleted successfully.");
				}
				catch (SQLException sqle) {
					System.out.println(sqle);
				}
				finally {
					try {
						st.close();
						con.close();
					}
					catch (SQLException sqle) {
						System.out.println(sqle);
					}
				}
			}
		});
		delBtn.setForeground(Color.WHITE);
		delBtn.setFont(new Font("Helvetica", Font.PLAIN, 15));
		delBtn.setBackground(new Color(0, 191, 255));
		delBtn.setBounds(33, 481, 122, 33);
		contentPane.add(delBtn);
		delBtn.setVisible(false);
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setFont(new Font("Helvetica", Font.PLAIN, 15));
		updateBtn.setBackground(new Color(0, 191, 255));
		updateBtn.setBounds(33, 481, 122, 33);
		contentPane.add(updateBtn);
		updateBtn.setVisible(false);
		addBtn.setForeground(new Color(255, 255, 255));
		addBtn.setBackground(new Color(0, 191, 255));
		addBtn.setFont(new Font("Helvetica", Font.PLAIN, 15));
		addBtn.setBounds(33, 481, 122, 33);
		contentPane.add(addBtn);
		addBtn.setVisible(false);
		
		Search = new JButton("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String qid = txtQuestionId.getText();
				
				Connection con = OracleConnection.getDbConnection();
				Statement st = null;
				ResultSet rs = null;
				try {
					
					st = con.createStatement();
					rs = st.executeQuery("select * from Quiz WHERE q_id = '" + qid + "'");
					if(rs.next()) {
						questionTxtArea.setText(rs.getString(2));
						txtOption1.setText(rs.getString(3));
						txtOption2.setText(rs.getString(4));
						txtOption3.setText(rs.getString(5));
						txtOption4.setText(rs.getString(6));
						txtAnswer.setText(rs.getString(7));
					}
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
		});
		Search.setForeground(Color.WHITE);
		Search.setFont(new Font("Helvetica", Font.PLAIN, 15));
		Search.setBackground(new Color(0, 191, 255));
		Search.setBounds(236, 45, 122, 33);
		contentPane.add(Search);
					
		
		if(operation.equals("ADD")) {
			Search.setVisible(false);
			addBtn.setVisible(true);
			scrollPane.setVisible(true);
			
			Connection con = OracleConnection.getDbConnection();
			Statement st = null;
			ResultSet rs = null;
			
			try {
				st = con.createStatement();
				rs = st.executeQuery("SELECT title FROM Examination");
				DefaultListModel dlm = new DefaultListModel();  
				dlm.addElement("Select Examination");  
				while(rs.next()) {
					dlm.addElement(rs.getString(1));  
				}
				list.setModel(dlm);
				list.setFixedCellHeight(30);
			}catch (Exception exp) {
				System.out.println(exp);
			}
			finally {
				try {
					rs.close();
					st.close();
					con.close();
				}catch(Exception exp) {}
			}
		}
		else if(operation.equals("UPDATE")) {
			updateBtn.setVisible(true);
			Search.setVisible(true);
			scrollPane.setVisible(false);
		}
		else if(operation.equals("DELETE")) {
			delBtn.setVisible(true);
			Search.setVisible(true);
			scrollPane.setVisible(false);
		}
	}
}
