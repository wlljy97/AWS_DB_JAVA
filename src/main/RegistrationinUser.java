package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import config.DBConnectionMgr;

public class RegistrationinUser extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTextField;
	private JTextField passwordTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationinUser frame = new RegistrationinUser();
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
	public RegistrationinUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(12, 33, 176, 28);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordTextField = new JTextField();
		passwordTextField.setBounds(222, 33, 200, 28);
		contentPane.add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(12, 10, 57, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setBounds(222, 10, 57, 15);
		contentPane.add(lblNewLabel_1);
		
		JButton addUserButton = new JButton("추가");
		addUserButton.setBounds(12, 74, 410, 28);
		contentPane.add(addUserButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 112, 410, 139);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel
				(
			new Object[][] {
				{1, "aaa", "1234"},
				{null, null, null},
			},
			new String[] {
				"user_id", "username", "password"
			}
		));
		scrollPane.setViewportView(table);
		
	}
	
	public DefaultTableModel getUserTableModel() {
		String[] header = new String[] { "user_id", "username", "password" };
		List<List<Object>> userListAll = getUserListAll(); // mst 는 리스트에 리스트를 담는다.
		
		Object[][] userModelArray = new Object[userListAll.size()][userListAll.get(0).size()];
		
		for(int i = 0; i < userListAll.size(); i++) {
			for(int j = 0; j < userListAll.get(i).size(); j++) {
				userModelArray[i][j] = userListAll.get(i).get(j);
			}
		}
				
	}
}
